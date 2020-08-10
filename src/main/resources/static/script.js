


var database = [ { Username:"Sumitra", Password : "Birajdar" },
    { Username:"Pranay", Password : "Pandye" },
    { Username:"Atul", Password : "Shah" } ];

var	posts = [ {Name : "Laksh", Timeline: "Kind of Funny"}, {Name : "Mahavir", Timeline: "Tu Chutiya hai"}];

var nameprompt = prompt("What is your Username");
var passwordprompt =prompt("What is your Password");


function singIn(Name, Pass) {
    for (var i = 0; i < database.length; i++) {
        if (database[i].Username === nameprompt && database[i].Password === passwordprompt) {
            alert("Login sucesss")
        }
        else{
        database.lastIndexOf(i)}
    }
}

singIn(nameprompt,passwordprompt);
