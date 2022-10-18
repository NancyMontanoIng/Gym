function traerDatosCat(){
    //función para Consultar.
    $.ajax({
        url:"http://152.67.233.47:80/api/Category/all",
        //consultar
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            //Acá se puede validar la respuesta.
            //   console.log(respuesta);
            pintarDatos(respuesta);
        },
        error: function(respuesta, xhr){
            alert("Error de petición!")
        }
    });
}


function pintarDatos(datos) {
    let htmlParaInsertar = "";
    htmlParaInsertar += "<tr>";
    Object.keys(datos[0]).forEach(elemento =>{
        htmlParaInsertar+="<th>"+elemento+"</th>"
    });
    htmlParaInsertar += "</tr>";
    for (let i = 0; i < datos.length; i++) {
        htmlParaInsertar += "<tr>";
        Object.values(datos[i]).forEach(elemento =>{
            if(typeof(elemento)=='object') {
                htmlParaInsertar+= "</td>"+elemento[0].name+"</td>";
            }else {
                htmlParaInsertar += "<td>" + elemento + "</td>"
            }
        });
        htmlParaInsertar+="</tr>";

        for(let i=0; i<datos.length; i++){
            htmlParaInsertar +="<tr>";
            htmlParaInsertar += datos[i].name;
            htmlParaInsertar +="<tr>";
        }

    }
    //htmlParaInsertar += "</tr>";
    $("#tabla").empty();
    $("#tabla").append(htmlParaInsertar)

}