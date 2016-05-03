$(document).ready(function() {

    $('.custom-fileinput').fileinput({"allowedFileExtensions" : ["jpg", "png"]});

    $.fn.editable.defaults.mode = 'inline';

    //make username editable
    $('.editable').editable({
        success: function(response, newValue) {

            var id = $(this).attr('id');

            $('input[name='+id+']').val(newValue);
        }
    });

});