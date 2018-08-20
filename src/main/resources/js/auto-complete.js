function autoComplete() {
    let linkName = document.getElementById('search-input').value;
    console.log("Search performed for: " + linkName)
    $.getJSON({
        url: "http://localhost:5050/5ad3d9700000000000000000?key=" + linkName,
        success: function (result, status, xhr) {
            var table = $("<table class='autoCompleteTable'><thead><tr><th>Type</th><th>Name</th><th>Iata Code</th></tr><thead>");
            table.append("<tbody>")
            var tr;
            for (var i = 0; i < result.length; i++) {
                var buttonTitle = result[i].iata_code
                var searchQuery = result[i].name
                var navigate = "parent.open('https://www.google.co.uk/search?q="+searchQuery+"&oq="+ searchQuery+"')"
                tr = $("<tr>");
                tr.append("<td>" + result[i].type+ "</td>");
                tr.append("<td>" + result[i].name + "</td>");
                tr.append("<td>" + "<button name='table-row-button-"+i+"'"+
                "onclick="+ '"'+ navigate + '" '+
                "class='btn btn-sm btn-success' type='submit' value='"+buttonTitle+"'>"+buttonTitle+"</button>" +"</td>");
                tr.append("</tr>");
                table.append(tr);
            }
            table.append("</tbody>");
            table.append("</table>");
            $("#autoCompleteData").html(table);
        }
    });
    return false
}
