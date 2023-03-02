//--- TODO

// Variáveis Globais
var artigo1 = parseInt(document.getElementById("produto1").value);
var artigo2 = parseInt(document.getElementById("produto2").value);
var artigo3 = parseInt(document.getElementById("produto3").value);
var artigo4 = parseInt(document.getElementById("produto4").value);
var artigo5 = parseInt(document.getElementById("produto5").value);
var artigo6 = parseInt(document.getElementById("produto6").value);

var preco1 = parseFloat(document.getElementById("precoproduto1").value);
var preco2 = parseFloat(document.getElementById("precoproduto2").value);
var preco3 = parseFloat(document.getElementById("precoproduto3").value);
var preco4 = parseFloat(document.getElementById("precoproduto4").value);
var preco5 = parseFloat(document.getElementById("precoproduto5").value);
var preco6 = parseFloat(document.getElementById("precoproduto6").value);

var n_artigos = 0;
var valor_total = 0;

var Quantidades = document.getElementById("quantidades");
var Total = document.getElementById("total");

// Função comprar() : regista o artigo selecionado pelo cliente e chama a função calcular().
function comprar(number){
    switch(number){
        case 1:
            artigo1 += 1;
            break;
        case 2:
            artigo2 += 1;
            break;
        case 3:
            artigo3 += 1;
            break;
        case 4:
            artigo4 += 1;
            break;
        case 5:
            artigo5 += 1;
            break;
        case 6:
            artigo6 += 1;
            break;
        
        default:
            alert("O artigo não existe.");
    }

    calcular();
}

// Função calcular : calcula o número de artigos e o valor total com descontos.
function calcular(){

    var desconto = 0.95;

    n_artigos = artigo1 + artigo2 + artigo3 + artigo4 + artigo5 + artigo6;

    valor_total = ((artigo1*preco1) + (artigo2*preco2) + (artigo3*preco3) + (artigo4*preco4) + (artigo5*preco5) + (artigo6*preco6)).toFixed(2);
    
    if (valor_total > 100){
        valor_total = (valor_total * desconto).toFixed(2);
    }
    if (n_artigos >= 5){
        valor_total = (valor_total * desconto).toFixed(2);
    }
    Quantidades.innerText = n_artigos;
    Total.innerText = valor_total;
}

// Função validar() : verifica se existe pelo menos um artigo selecionado, validando o formulário.
function validar(){
    var valid = false
    if (n_artigos > 0){
        for (var i = 1; i <= 6; i++){
            document.getElementById("produto"+i).value = window["artigo" + i];
        }
        valid = true;
    } else {
        alert("Tem de selecionar pelo menos um produto.")
        valid = false;
    }
    return valid;
}

// Função limpar() : dá um "reset" às variáveis globais.
function limpar(){
    artigo1 = 0;
    artigo2 = 0;
    artigo3 = 0;
    artigo4 = 0;
    artigo5 = 0;
    artigo6 = 0;

    for (var i = 1; i <= 6; i++){
        document.getElementById("produto" + i).value = 0;
    }
    n_artigos = 0;
    valor_total = 0;
    Quantidades.innerText = n_artigos;
    Total.innerText = valor_total;

}