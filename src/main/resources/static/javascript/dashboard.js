$(document).ready(function () {
    $("#dashboardlink").click(function () {
        console.log("logged");
        $("#products-li").removeClass("active");
        $("#customer-li").removeClass("active");
        $("#dashboard-li").addClass("active")
    });

    $("#products").click(function () {
        $("#dashboard-li").removeClass("active");
        $("#customer-li").removeClass("active");
        $("#products-li").addClass("active")
    });

    $("#customers").click(function () {
        $("#dashboard-li").removeClass("active");
        $("#products-li").removeClass("active");
        $("#customer-li").addClass("active")
    });
});