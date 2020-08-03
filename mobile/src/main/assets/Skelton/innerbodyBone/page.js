var page = new WebPage()
var fs = require('fs');
var args = require('system').args;
//var args = system.args;


if (typeof args[1] === 'undefined') {
    phantom.exit();
}
console.log(args);

function waitFor(testFx, onReady, timeOutMillis) {
    var maxtimeOutMillis = timeOutMillis ? timeOutMillis : 3000, //< Default Max Timout is 3s
            start = new Date().getTime(),
            condition = false,
            interval = setInterval(function () {
                if ((new Date().getTime() - start < maxtimeOutMillis) && !condition) {
                    // If not time-out yet and condition not yet fulfilled
                    condition = (typeof (testFx) === "string" ? eval(testFx) : testFx()); //< defensive code
                } else {
                    if (!condition) {
                        // If condition still not fulfilled (timeout but condition is 'false')
                        console.log("'waitFor()' timeout");
                        typeof (onReady) === "string" ? eval(onReady) : onReady(); //< Do what it's supposed to do once the condition is fulfilled
                        clearInterval(interval);
                    } else {
                        // Condition fulfilled (timeout and/or condition is 'true')
                        console.log("'waitFor()' finished in " + (new Date().getTime() - start) + "ms.");
                        typeof (onReady) === "string" ? eval(onReady) : onReady(); //< Do what it's supposed to do once the condition is fulfilled
                        clearInterval(interval); //< Stop this interval
                    }
                }
            }, 250); //< repeat check every 250ms
}

page.open("http://www.innerbody.com" + args[1], function (status) {
    if (status !== "success") {
        console.log("webpage does not exist");
        phantom.exit();
    }
});

page.onLoadFinished = function () {
    page.includeJs("http://localhost/innerbody/jquery.min.js", function () {
        waitFor(function () {
            return page.evaluate(function () {
                return $("#image-map .leaflet-pane .leaflet-overlay-pane svg").length;
            });
        }, function () {
            var html = page.evaluate(function () {
                return [
                    $("#image-map .leaflet-pane .leaflet-pane.leaflet-overlay-pane").html(),
                    $("#image-map .leaflet-pane .leaflet-pane.leaflet-marker-pane").html()];
            });
            var relativePath = ""
            for (i = 0; i < args[1].split("/").length - 1; i++) {
                relativePath += "../";
            }
            var layout = fs.read("layout.html").replace(/{{graphics}}/g, html[0]).replace(/{{markers}}/g, html[1]).replace(/{{style}}/g, relativePath);
            fs.write('output/' + args[1].replace(new RegExp('.html$'), '') + ".html", layout, 'w');
            phantom.exit(0);
        }, 10000);
    });
};