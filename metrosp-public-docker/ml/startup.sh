#!/bin/bash
cd /opt/open-chatbot/ml && waitress-serve --port=5000 --call flaskr:create_app