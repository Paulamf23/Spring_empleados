
    /*<![CDATA[*/
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            var alertas = document.querySelectorAll('.custom-alert');
            alertas.forEach(function (alerta) {
                alerta.style.display = 'none';
            });
        }, 4000);
    });
    /*]]>*/
