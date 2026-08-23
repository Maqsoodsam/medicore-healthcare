# MediCore Error Response Contract

MediCore uses a consistent structure when returning API errors.

## Required Fields

- **status** – HTTP status code
- **error** – Type of error
- **message** – Description of the problem
- **path** – API endpoint where the error occurred
- **timestamp** – Time the error occurred

## Example Error Response

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Patient not found with ID: 25",
  "path": "/patients/25",
  "timestamp": "2026-08-13T10:30:00"
}