$('.countdown').each(function () {
    var $this = $(this);
    var finalDate = parseDate($this.parent().find('.finishdate').html());
    var stop = false;
    $this.countdown(finalDate, function (event) {
        $this.html(event.strftime('%D days %H:%M:%S Hs'));
        window.console.log(' o ');
        // hours left < 24
        if (event.offset.totalHours < 24) {
            $($this).removeClass('bg_green').addClass('bg_red');
        }
        stop = specialCountDownOperations($this, event, stop);
    });
});

function specialCountDownOperations(ref, event, stop) {
    return stop;
}

function parseDate(date) {
    var a = date.split(/[^0-9]/);
    return new Date(a[0], a[1] - 1, a[2], a[3], a[4], a[5]);
}