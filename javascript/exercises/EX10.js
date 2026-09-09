function CalcularConsumo(litros, distancia) {
  let consumo = (litros / distancia) * 100;
  
  let classificacio;
  if (consumo < 5) {
    classificacio = "Muy Eficiente";
  } else if (consumo >= 5 && consumo <= 8) {
    classificacio = "Eficiente";
  } else {
    classificacio = "Consumo elevado";
  }

  return { consum: consumo, classificacio };
}

let litros = parseFloat(prompt("Introduce los litros:"));
let distancia = parseFloat(prompt("Introduce la distancia:"));

let resultat = CalcularConsumo(litros, distancia);
let missatge = "El cotche ha consumido: " + resultat.consum + " L/100 km - " + resultat.classificacio;

console.log(missatge);
alert(missatge);
