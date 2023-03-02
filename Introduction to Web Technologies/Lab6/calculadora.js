
var controlo = 1;
var op1_string = "";
var op2_string = "";
var op1;
var op2;
var operation;
var res_div = document.getElementById("res");

function addNumber(){
    var num = event.target.value;

    if (controlo == 1){
        op1_string += num;
        op1 = parseInt(op1_string);
        res_div.innerHTML = op1_string;
    

    }

    else {
        op2_string += num;
        op2 = parseInt(op2_string);
        res_div.innerHTML = op2_string;
    }
}


function addOperator(){

    var resultado

    if (controlo == 2){

        switch (operation) {

            case "+":
                resultado = op1 + op2;
                break;

            case "-":
                resultado = op1 - op2;
                console.log(op1);
                console.log(op2);
                console.log(operation);
                console.log(resultado);
                break;

            case "*":
                resultado = op1 * op2;
                break;
            
            case "/":
                    
                if (op2 == 0){
                    resultado = "Undefined. Cannot divide by 0";
                }

                else {
                    resultado = op1 / op2;
                }

                break;

            default:
                resultado = op1 - op2;
        }
        res_div.innerHTML = resultado;
        controlo = 0;
    }

    else {

        operation = event.target.value;
        res_div.innerHTML += operation;
        controlo = 2;

    }  
}

function rst () {
    controlo = 1;
    op1_string = "";
    op2_string = "";
    res_div.innerHTML = "0";
}
