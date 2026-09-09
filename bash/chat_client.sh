#!/bin/bash

# Incluir las funciones de encriptación/desencriptación
source ./encrypt_decrypt.sh

# Broker MQTT local (arranca tu propio Mosquitto: mosquitto -v)
BROKER="127.0.0.1"
PORT="1883"
TOPIC="chat/general"

# Función para enviar mensajes
send_message() {
  local message="$1"
  local encrypted_message=$(encrypt_message "$message")
  mosquitto_pub -h "$BROKER" -p "$PORT" -t "$TOPIC" -m "$encrypted_message"
  echo "Mensaje enviado: $message (encriptado)"
}

# Función para recibir mensajes
receive_messages() {
  mosquitto_sub -h "$BROKER" -p "$PORT" -t "$TOPIC" -q 1 | while read -r line; do
    # mosquitto_sub -q 1 asegura que el payload viene en una sola línea
    # y no incluye el tópico si solo hay un tópico suscrito.
    # Si se suscribe a múltiples tópicos, mosquitto_sub incluirá el tópico.
    # Para este caso simple, asumimos un solo tópico.
    local encrypted_message="$line"
    local decrypted_message=$(decrypt_message "$encrypted_message")
    echo "Mensaje recibido: $decrypted_message"
  done
}

# Exportar funciones
export -f send_message
export -f receive_messages


