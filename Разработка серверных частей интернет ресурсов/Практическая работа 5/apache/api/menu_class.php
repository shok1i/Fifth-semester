<?php
    header('Content-Type: application/json');

    class menu_class {
        private $db;

        public function __construct() {
            $this->db = new mysqli("db", "user", "password", "appDB");
            if ($this->db->connect_error) {
                die(json_encode(['error' => 'Database connection failed: ' . $this->db->connect_error]));
            }
        }

        public function getAllCoffees() {
            $query = "SELECT * FROM Menu";
            $result = $this->db->query($query);
            $coffees = $result ? $result->fetch_all(MYSQLI_ASSOC) : [];
            return $coffees;
        }

        public function getCoffee($id) {
            $stmt = $this->db->prepare("SELECT id, name, price FROM Menu WHERE id = ?");
            $stmt->bind_param("i", $id);
            $stmt->execute();
            $result = $stmt->get_result();

            return $result->num_rows > 0 ? $result->fetch_assoc() : ['error' => 'id not found'];
        }

        public function createCoffee($name, $price) {
            if (!$name || !$price) {
                return ['error' => 'Name and price cannot be null'];
            }

            $stmt = $this->db->prepare("INSERT INTO Menu (name, price) VALUES (?, ?)");
            $stmt->bind_param("sd", $name, $price);

            return $stmt->execute() ? ['success' => 'Coffee added successfully', 'id' => $this->db->insert_id] : ['error' => 'Database error: ' . $stmt->error];
        }

        public function updateCoffee($id, $name, $price) {
            if (!$id || !$name || !$price) {
                return ['error' => 'Missing parameters for update'];
            }

            $stmt = $this->db->prepare("UPDATE Menu SET name = ?, price = ? WHERE id = ?");
            $stmt->bind_param("sdi", $name, $price, $id);

            return $stmt->execute() ? ['success' => 'Coffee updated successfully'] : ['error' => 'Failed to update coffee: ' . $stmt->error];
        }

        public function deleteCoffee($id) {
            $stmt = $this->db->prepare("DELETE FROM Menu WHERE id = ?");
            $stmt->bind_param("i", $id);
            return $stmt->execute() ? ['success' => 'Coffee deleted successfully'] : ['error' => 'Failed to delete coffee'];
        }
    }