function Bisiesto(año) {
  if ((año % 4 === 0 && año % 100 !== 0) || (año % 400 === 0)) {
    return "Año de traspaso";
  } else {
    return "No año de traspaso";
  }
}

let Año = parseInt(prompt("Introduce el año:"));
let Resultado = Bisiesto(Año);

console.log(Resultado);
alert(Resultado);
