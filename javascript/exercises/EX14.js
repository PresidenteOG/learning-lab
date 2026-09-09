let Libro = {
  titulo: "L’ombra del vent",
  autor: "Carlos Ruiz Zafón",
  publicacion: 2001,
  disponible: true,
  Informacion: function () {
    console.log("Titulo: " + this.titulo);
    console.log("Autor: " + this.autor);
    console.log("Año de la publicacion: " + this.publicacion);
    if (this.categoria) {
        console.log("Categoria: " + Libro.categoria);
    }
    if (this.disponible === true){
        console.log("Disponible: Si");
    } else {
        console.log("Disponible: No");
    }
  }
};
console.log("")
console.log("Antes de la modificacion:");
console.log("")

Libro.Informacion();
Libro.publicacion = 2006;
Libro.disponible = false;
Libro.categoria = "Intriga";

console.log("")
console.log("Despues de la modificacion:");
console.log("")

Libro.Informacion();
