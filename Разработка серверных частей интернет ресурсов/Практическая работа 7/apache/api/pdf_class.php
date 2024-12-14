<?php
header('Content-Type: application/json');

class pdf_class
{
    private $db;

    public function __construct()
    {
        $this->db = new mysqli("db", "user", "password", "appDB");
        if ($this->db->connect_error) {
            die(json_encode(['error' => 'Database connection failed: ' . $this->db->connect_error]));
        }
    }

    public function getPDF($id)
    {
        $stmt = $this->db->prepare("SELECT * FROM pdfs WHERE ID = ?");
        $stmt->bind_param("i", $id);
        $stmt->execute();
        $result = $stmt->get_result();
        $pdf = $result->fetch_assoc();

        if ($pdf) {
            header('Content-Type: application/pdf');
            header('Content-Length: ' . filesize($pdf['filepath']));
            readfile($pdf['filepath']);
        } else {
            echo json_encode(['error' => 'PDF not found']);
        }
    }

    public function uploadPDF($file)
    {
        $upload_dir = '/var/www/html/uploads/';
        $filename = basename($file['name']);
        $file_path = $upload_dir . uniqid() . '_' . $filename;

        if ($file['error'] === UPLOAD_ERR_OK) {
            if (move_uploaded_file($file['tmp_name'], $file_path)) {
                $stmt = $this->db->prepare("INSERT INTO pdfs (filename, filepath) VALUES (?, ?)");
                $stmt->bind_param("ss", $filename, $file_path);

                if ($stmt->execute()) {
                    return ['success' => 'PDF uploaded successfully', 'id' => $this->db->insert_id];
                } else {
                    return ['error' => 'Failed to save file info to database: ' . $stmt->error];
                }
            } else {
                return ['error' => 'Failed to move uploaded file'];
            }
        } else {
            return ['error' => 'File upload error: ' . $file['error']];
        }
    }
}