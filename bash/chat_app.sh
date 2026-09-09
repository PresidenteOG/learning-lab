#!/bin/bash

# Incluir el cliente MQTT y las funciones de encriptación/desencriptación
source ./chat_client.sh

# Limpiar la pantalla
clear

echo "Bienvenido al Chat MQTT (broker local)"
echo "---------------------------"

# Iniciar el receptor de mensajes en segundo plano
receive_messages &
RECEIVER_PID=$!

# Bucle principal para enviar mensajes
while true; do
  read -p "Tu mensaje: " user_message
  if [[ -z "$user_message" ]]; then
    continue
  fi
  send_message "$user_message"
done

# Limpiar al salir (esto no se alcanzará en el bucle infinito, pero es buena práctica)
trap "kill $RECEIVER_PID" EXIT


