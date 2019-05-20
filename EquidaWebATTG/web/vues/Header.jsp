<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Equida - ${param.NomPage}</title>

    <meta name = "viewport" content = "width = device-width, initial-scale = 1">      
    <link rel = "stylesheet"
          href = "https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel = "stylesheet"
          href = "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type = "text/javascript" src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src = "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>	 
    
    
    <!--<script type = "text/javascript" src="https://raw.githubusercontent.com/jquery/jquery-ui/master/ui/i18n/datepicker-fr.js"> </script>-->
    
    <script>
    $(document).ready(function () {
            $(".dropdown-trigger").dropdown();
            $('select').formSelect();
            $(".datepicker").datepicker({
                    format : 'yyyy-mm-dd'
                });
            //$(".datepicker").setDefaults( $.datepicker.regional["fr"] );
        

        });
    </script>
</head>