//--- variáveis globais

var qtd1 = document.getElementById("Status1");
var qtdX = document.getElementById("StatusX");
var qtd2 = document.getElementById("Status2");
var _button = document.getElementById("botao");

var linhas = 0;
var apostas = 1;
var selected1 = 0;
var selectedX = 0;
var selected2 = 0;
//--- função ativada sempre que se carrega em qualquer das caixas
function boxClicked() {
    var linhas = 0;
    var apostas = 1;
    var selected1 = 0;
    var selectedX = 0;
    var selected2 = 0;


    for (var i = 1; i <= 13; i++){
        var apostasNaLinha = 1;
        var x = document.getElementById("Jogo" + i + "_1").checked;
        var y= document.getElementById("Jogo" + i + "_x").checked;
        var z = document.getElementById("Jogo" + i + "_2").checked;


        if(x) selected1++;
        if(y) selectedX++;
        if(z) selected2++;


        if(x | y | z) linhas++;

        if((x && y) || (x && z) || (y && z)) apostasNaLinha = 2;

        if (x && y && z) apostasNaLinha = 3;

        apostas += apostasNaLinha;
    }
    console.log(apostas);
    setStatus();

}


function setStatus(){
    qtd1.innerText = selected1;
    qtdX.innerText = selectedX;
    qtd2.innerText = selected2;

    if (linhas == 13 && apostas >= 1 && apostas <= 384){
        var x = document.getElementById("multiplas" + apostas);

        if(x != null) x.checked = true;

        _button.disabled = "disabled";
    }
}

function doAction(){
    alert("Aposta efetuada com sucesso!");
}


document.getElementById("botao").addEventListener("click", doAction);