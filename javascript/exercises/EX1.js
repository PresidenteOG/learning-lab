var nom = prompt("Escriu el teu nom:")
var Letra1 = nom.charAt(0).toUpperCase()
var Slice = nom.slice(1).toLowerCase()
var counter = nom.length
console.log("Te llamas " +Letra1 + Slice + " Y tienes " + counter + " Letras");
