<?php
    header('Content-Type: application/json');

    class order_class {
        private $db;

        public function __construct() {
            $this->db = new mysqli("db", "user", "password", "appDB");
            if ($this->db->connect_error) {
                die(json_encode(['error' => 'Database connection failed: ' . $this->db->connect_error]));
            }
        }

        public function getAllOrders() {
            $query = "SELECT * FROM Order";
            $result = $this->db->query($query);
            $coffees = $result ? $result->fetch_all(MYSQLI_ASSOC) : [];
            return $coffees;
        }

        public function getOrder($id) {
            $stmt = $this->db->prepare("SELECT id, name, price FROM Orders WHERE id = ?");
            $stmt->bind_param("i", $id);
            $stmt->execute();
            $result = $stmt->get_result();

            return $result->num_rows > 0 ? $result->fetch_assoc() : ['error' => 'id not found'];
        }

        public function createOrder($name, $price) {
            if (!$name || !$price) {
                return ['error' => 'Name and price cannot be null'];
            }

            $stmt = $this->db->prepare("INSERT INTO Orders (name, price) VALUES (?, ?)");
            $stmt->bind_param("sd", $name, $price);

            return $stmt->execute() ? ['success' => 'Coffee added successfully', 'id' => $this->db->insert_id] : ['error' => 'Database error: ' . $stmt->error];
        }

        public function updateOrder($id, $name, $price) {
            if (!$id || !$name || !$price) {
                return ['error' => 'Missing parameters for update'];
            }

            $stmt = $this->db->prepare("UPDATE Orders SET name = ?, price = ? WHERE id = ?");
            $stmt->bind_param("sdi", $name, $price, $id);

            return $stmt->execute() ? ['success' => 'Coffee updated successfully'] : ['error' => 'Failed to update coffee: ' . $stmt->error];
        }

        public function deleteOrder($id) {
            $stmt = $this->db->prepare("DELETE FROM Orders WHERE id = ?");
            $stmt->bind_param("i", $id);
            return $stmt->execute() ? ['success' => 'Coffee deleted successfully'] : ['error' => 'Failed to delete coffee'];
        }
    }