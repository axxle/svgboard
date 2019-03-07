var SVG_BOARD_ID = "svgboard";	
var scaleRates = [
	4,
	3,
	2.5,
	2,
	1.8,
	1.5,
	1.2,
	1,
	0.5,
	0.25,
	0.2,
	0.15,
	0.075
];
var singleScaleRateIndex = findInitScaleIndex(scaleRates);
var currentScaleIndex = singleScaleRateIndex;
function findInitScaleIndex(scaleRateArr) {
	for(var i = 0; i < scaleRateArr.length; i++){
		if (scaleRateArr[i] == 1 ) {
			return i;
		}
	}
}

var TOOLBAR_HEIGHT = 30;
var initWidth=document.documentElement.clientWidth;
var initHeight=document.documentElement.clientHeight;

var scaleValues = calcScaleValues(scaleRates);
/*Построение таблицы масштабирования*/
function calcScaleValues(scaleRateArr) {
	var newScaleValueArr = [];
	for (var i = 0; i < scaleRateArr.length; i++) {
		var w = 0;
		var h = 0;
		if (scaleRateArr[i]<0) {
			w = initWidth/(-scaleRateArr[i]);
			h = initHeight/(-scaleRateArr[i]);
		}
		if (scaleRateArr[i]>=0) {
			w = initWidth*(scaleRateArr[i]);
			h = initHeight*(scaleRateArr[i]);
		}
		newScaleValueArr.push([0, 0, w, h]);
	}
	return newScaleValueArr;
}


/*Считает значения для viewBox при перетаскивании мышкой*/
function calcMouseMove(mouseDownVBvalues, mouseDownXY, mouseMoveXY, currentScaleIndex) {
	var rate = scaleRates[currentScaleIndex];
	var diffX = mouseDownXY[0] - mouseMoveXY[0];
	var diffY = mouseDownXY[1] - mouseMoveXY[1];
	var castedDiffX = diffX*rate;
	var castedDiffY = diffY*rate;
	var shiftedDiffX = castedDiffX + mouseDownVBvalues[0];
	var shiftedDiffY = castedDiffY + mouseDownVBvalues[1];
	var values = scaleValues[currentScaleIndex];
	return [shiftedDiffX, shiftedDiffY, values[2], values[3]];
}


/*Обработчик событий прокрутки мыши*/
document.onwheel = function(e) {
	e = e || window.event;
	var delta = e.deltaY || e.detail || e.wheelDelta;
	var mouseX = e.clientX;
	var mouseY = e.clientY;
	setViewBoxValues(SVG_BOARD_ID, getScaleValues(delta, mouseX, mouseY));
};

/*Обработка перетаскивания полотна доски*/
var svgBoardObject = document.getElementById(SVG_BOARD_ID);
svgBoardObject.onmousedown = function(event) {
	var mouseDownXY = [event.clientX, event.clientY];
	var mouseDownVBvalues = getViewBoxValues(SVG_BOARD_ID);
	document.onmousemove = function(e) {
		var mouseMoveXY = [e.clientX, e.clientY];
		var moveViewBox = calcMouseMove(mouseDownVBvalues, mouseDownXY, mouseMoveXY, currentScaleIndex);
		setViewBoxValues(SVG_BOARD_ID, moveViewBox);
	}
	svgBoardObject.onmouseup = function(e) {
        saveBoardSetting(""); //
		document.onmousemove = null;
		svgBoardObject.onmouseup = null;
	}
}

var getViewBoxValues = function (id) { 
  var strArr = document.getElementById(id).getAttribute("viewBox").split(" ");
  var arr = [];
  for(var i = 0; i < strArr.length; i++){
	arr[i] = parseInt(strArr[i], 10);
  }
  return arr;
}

var setViewBoxValues = function (id, arr) { 
  var svg = document.getElementById(id);
  svg.setAttribute("viewBox", arr.join(' '));
}

/*if (!String.prototype.trim) {
    (function() {
        // Вырезаем BOM и неразрывный пробел
        String.prototype.trim = function() {
            return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
        };
    })();
}*/

function initBoard(boardId) {
    var board = document.getElementById(SVG_BOARD_ID);
    initWidth=document.documentElement.clientWidth;
    initHeight=document.documentElement.clientHeight;
    board.setAttribute('width', initWidth);
    board.setAttribute('height', initHeight);
    getAndSetBoardSettingsFromServer();
}

/*Самовызываемая функция*/
(function (){
	initBoard(SVG_BOARD_ID);
}());

function castSingleRate(mouseX, mouseY, currentScaleIndex) {
	var rate = scaleRates[currentScaleIndex];
	var castedX = mouseX*rate;
	var castedY = mouseY*rate;
	return [castedX, castedY];
}
	
/*Получение данных для масштабирования через viewBox*/
function getScaleValues(wheelDelta, mouseX, mouseY) {
	var k = wheelDelta < 0 ? 1 : -1;
	var prevScaleIndex = currentScaleIndex;
	var nextScaleIndex = prevScaleIndex + k;
	if (prevScaleIndex < 0) {
		prevScaleIndex = 0;
	}
	if (prevScaleIndex >= scaleValues.length-1) {
		prevScaleIndex = scaleValues.length-1;
	}
	if (nextScaleIndex < 0) {
		nextScaleIndex = 0;
	}
	if (nextScaleIndex >= scaleValues.length-1) {
		nextScaleIndex = scaleValues.length-1;
	}
	var viewBoxCurrent = getViewBoxValues(SVG_BOARD_ID);
	var newValues = scaleValues[nextScaleIndex];
	var lastRate = scaleRates[currentScaleIndex];
	var newRate = scaleRates[nextScaleIndex];
	
	var shiftedArr = [
		viewBoxCurrent[0] + (mouseX*lastRate - mouseX*newRate),
		viewBoxCurrent[1] + (mouseY*lastRate - mouseY*newRate),
		newValues[2],
		newValues[3]
	]
	currentScaleIndex = nextScaleIndex;
	return shiftedArr;
}

/*Получение сохраненных настроек доски*/
function getAndSetBoardSettingsFromServer() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "board/",
        cache: false,
        success: function(settingsFromServer){
            //console.log('Получено с сервера: ' + settingsFromServer);
			var s = settingsFromServer;
            var board = document.getElementById(SVG_BOARD_ID);
            //console.log("s.viewbox = " + s.viewbox);
            //console.log("s.width=" + parseInt(s.width.trim(), 10));
            //console.log("s.height=" + parseInt(s.height.trim(), 10));
            //setViewBoxValues(SVG_BOARD_ID, [0, 0, initWidth, initHeight]);
            board.setAttribute("viewBox", s.viewbox);
            //board.setAttribute('width', s.width);
            //board.setAttribute('height', s.height);
        }
    });
}

/*Сохранение настроек доски на сервере*/
function saveBoardSetting(settings) {
    //хочу отправлять на сервер настройки, с задержкой 1 сек,
    // со сбросом таймера при последующих изменениях:
    //	1. При перетаскивании, один раз после отпускания кнопки
    //	2. При масштабировании
    var width = document.getElementById(SVG_BOARD_ID).getAttribute("width");
    var height = document.getElementById(SVG_BOARD_ID).getAttribute("height");
    var viewbox = document.getElementById(SVG_BOARD_ID).getAttribute("viewBox");
    var boardSetting = {"id":1,"width":width,"height":height,"viewbox":viewbox};
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(boardSetting),
        url: "board/",
        cache: false,
        success: function(savedFromServer){
            console.log('Сохранено и получено с сервера: ' + savedFromServer);
        }
    });
}
