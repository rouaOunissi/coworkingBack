package com.esprit.coworkingspaceback.roua.controller;

public class ApiResponse {

        private String message;

        public ApiResponse(String message) {
            this.message = message;
        }

        // Getter and setter
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


