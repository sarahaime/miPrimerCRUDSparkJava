
(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    var nombre = $('.validate-input input[name="nombre"]');
    var apellido = $('.validate-input input[name="apellido"]');
    var matricula = $('.validate-input input[name="matricula"]');
    var telefono = $('.validate-input textarea[name="telefono"]');



    $('.validate-form').on('submit',function(){
        var check = true;

        if($(nombre).val().trim() == ''){
            showValidate(nombre);
            check=false;
        }

        if($(apellido).val().trim() == ''){
            showValidate(apellido);
            check=false;
        }


        if($(matricula).val().trim() == ''){
            showValidate(matricula);
            check=false;
        }

        if($(telefono).val().trim() == ''){
            showValidate(telefono);
            check=false;
        }

        console.log(check);
        return check;
    });


    $('.validate-form .input1').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }


})(jQuery);