function Chino(año) {
  let animales = [
    "Mono 🐵", "Gallo 🐔", "Perro 🐶", "Cerdo 🐷", "Rata 🐭", "Buey 🐮",
    "Tigre 🐯", "Conejo 🐰", "Dragón 🐲", "Serpiente 🐍", "Caballo 🐴", "Cabra 🐐"
  ];

  return animales[año % 12];
}

let año = parseInt(prompt("Año zodiaco:"));
let signo = Chino(año);
let mensaje = "El año " + año + " corresponde a: " + signo;

console.log(mensaje);
alert(mensaje);
