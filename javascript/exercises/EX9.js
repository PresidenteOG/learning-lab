let total = 0;

function agregarCarret(preu) {
  total += preu;
  return total;
}

function calcularImpost(total) {
  return total * 1.21;
}

agregarCarret(200);
agregarCarret(300);
agregarCarret(400);

console.log("Total acumulat després d'afegir els productes -> " + total);
console.log("El total a pagar és de " + calcularImpost(total));
