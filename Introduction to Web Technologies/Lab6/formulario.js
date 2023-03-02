/* Função de validação  */
function validate() {
    var retVal = true; /* Vamos partir do princípio de que o formulário está válido ... */
/* TODO */

    var _nome = document.getElementById("Nome");
    var _nomeError = document.getElementById("NomeError");
    if (_nome.value.trim().length < 3) {
            retVal = false;
            _nomeError.classList.add("d-block");
            _nomeError.classList.remove("d_none");
    }

    else {
        _nomeError.classList.remove("d-block");
        _nomeError.classList.add("d_none");
    }
    
    var _morada = document.getElementById("Morada"); 
    var _moradaError = document.getElementById("MoradaError");
    var palavrasArray = _morada.value.split(' ');

    if (palavrasArray.length < 3) {
        retVal = false;
        _moradaError.classList.add("d-block");
        _moradaError.classList.remove("d_none"); 
     }

    else {
        _moradaError.classList.remove("d-block");
        _moradaError.classList.add("d_none");
    }


    var _cursoSelecionado = document.getElementById("Curso").selectedIndex; /* Se o indice é zero, não está nenhum curso selecionado */
    var _cursoError = document.getElementById("CursoError");
    if (_cursoSelecionado == 0) {
        retVal = false;
        _cursoError.classList.add("d-block");
        _cursoError.classList.remove("d_none");
    }

    else {
        _cursoError.classList.remove("d-block");
        _cursoError.classList.add("d_none");
    }

    var _inputElemsArray = document.getElementsByTagName("input");
    var _inputElemsArrayError = document.getElementById("VehicleError");
    var count = 0;
    for (var i = 0; i < _inputElemsArray.length; i++) {
        /* Contar quais os que são do tipo checkbox e estão selecionados */
        if (_inputElemsArray[i].type == "checkbox" && _inputElemsArray[i].checked == true) {
            count++; 
        }
    }

    if (count < 2) {
        retVal = false;
        _inputElemsArrayError.classList.add("d-block");
        _inputElemsArrayError.classList.remove("d_none");
    }

    else {

        _inputElemsArrayError.classList.remove("d-block");
        _inputElemsArrayError.classList.add("d_none");
    }
    

    var _cores = document.querySelectorAll('input[name="cor"]:checked').length; 
    var _coresError = document.getElementById("CorError");
    if (_cores == 0) {
        retVal = false;
        _coresError.classList.add("d-block");
        _coresError.classList.remove("d_none");
    }

    else {
        _coresError.classList.remove("d-block");
        _coresError.classList.add("d_none");
    }

    return retVal;

}
