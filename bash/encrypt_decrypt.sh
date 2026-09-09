#!/bin/bash

# Clave y sal para encriptación/desencriptación (DEBEN SER LAS MISMAS EN AMBOS LADOS)
# Usar valores fijos para la clave y la sal. En un entorno real, estos deberían ser gestionados de forma segura.
ENCRYPTION_KEY="your_secret_key_here"
SALT="your_salt_here"

encrypt_message() {
  local message="$1"
  # -a para base64 encoding/decoding, -salt para generar una sal aleatoria (se guarda en el output encriptado)
  # -k para la clave, -pbkdf2 -iter para derivación de clave más segura
  echo -n "$message" | openssl enc -aes-256-cbc -a -salt -pbkdf2 -iter 10000 -k "$ENCRYPTION_KEY"
}

decrypt_message() {
  local encrypted_message="$1"
  # -d para desencriptar, -a para base64 decoding, -k para la clave
  # openssl extraerá la sal del mensaje encriptado automáticamente
  echo "$encrypted_message" | openssl enc -aes-256-cbc -d -a -pbkdf2 -iter 10000 -k "$ENCRYPTION_KEY" 2>/dev/null
}

# Exportar funciones para que estén disponibles en subshells
export -f encrypt_message
export -f decrypt_message
export ENCRYPTION_KEY
export SALT


