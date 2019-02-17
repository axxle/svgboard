/*Получение сохраненных настроек доски*/
function getBoardSettings() {
	//получить настройки с сервера
    var width = "1920";
    var height = "908";
    var viewbox = "0 0 1920 908";
    //засетить в нужные аттрибуты
    //var svg = document.getElementById(id);
    //svg.setAttribute("viewBox", viewbox);
    console.log("Получение настроек доски с сервера: "
        , "width=", width, " height=", height, " viewbox=", viewbox);
}

/*Сохранение настроек доски на сервере*/
function saveBoardSetting(settings) {
    var width = document.getElementById(id).getAttribute("width");
    var height = document.getElementById(id).getAttribute("height");
    var viewbox = document.getElementById(id).getAttribute("viewBox");
    console.log("Отправка настроек доски на сервер: "
		, "width=", width, " height=", height, " viewbox=", viewbox);
}
