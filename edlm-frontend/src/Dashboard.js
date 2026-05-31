
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./App.css";

function Dashboard() {
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [showAdd, setShowAdd] = useState(false);
  const [addForm, setAddForm] = useState({ firstName: "", lastName: "", email: "", ssn: "", status: "ACTIVE" });
  const [addError, setAddError] = useState("");
  const [editCustomer, setEditCustomer] = useState(null);
  const [editForm, setEditForm] = useState({ firstName: "", lastName: "", email: "", ssn: "", status: "ACTIVE" });
  const [editError, setEditError] = useState("");
  const [deleteId, setDeleteId] = useState(null);
  const [deleteError, setDeleteError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    setLoading(true);
    setError("");
    try {
      const token = localStorage.getItem("authToken");
      const response = await fetch("http://localhost:8081/api/customers", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (!response.ok) throw new Error("Failed to fetch customers");
      const data = await response.json();
      setCustomers(data);
    } catch (err) {
      setError("Could not load customers.");
    } finally {
      setLoading(false);
    }
  };

  const handleEditClick = (customer) => {
    setEditCustomer(customer);
    setEditForm({
      firstName: customer.firstName,
      lastName: customer.lastName,
      email: customer.email,
      ssn: customer.ssn,
      status: customer.status,
    });
    setEditError("");
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    setEditError("");
    try {
      const token = localStorage.getItem("authToken");
      const response = await fetch(`http://localhost:8081/api/customers/${editCustomer.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(editForm),
        }
      );
      if (!response.ok) throw new Error("Failed to update customer");
      setEditCustomer(null);
      fetchCustomers();
    } catch (err) {
      setEditError("Could not update customer.");
    }
  };

  const handleDeleteClick = (id) => {
    setDeleteId(id);
    setDeleteError("");
  };

  const handleDeleteConfirm = async () => {
    setDeleteError("");
    try {
      const token = localStorage.getItem("authToken");
      const response = await fetch(`http://localhost:8081/api/customers/${deleteId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (!response.ok) throw new Error("Failed to delete customer");
      setDeleteId(null);
      fetchCustomers();
    } catch (err) {
      setDeleteError("Could not delete customer.");
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    navigate("/login");
  };

  const handleAddCustomer = async (e) => {
    e.preventDefault();
    setAddError("");
    try {
      const token = localStorage.getItem("authToken");
      const response = await fetch("http://localhost:8081/api/customers", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(addForm),
      });
      if (!response.ok) throw new Error("Failed to add customer");
      setShowAdd(false);
      setAddForm({ firstName: "", lastName: "", email: "", ssn: "", status: "ACTIVE" });
      fetchCustomers();
    } catch (err) {
      setAddError("Could not add customer.");
    }
  };

  return (
    <div className="dashboard-container">
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h1>Customer Dashboard</h1>
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </div>
      <div style={{ margin: "20px 0" }}>
        <button className="add-btn" onClick={() => setShowAdd(true)}>Add Customer</button>
      </div>
      {showAdd && (
        <div className="modal-backdrop">
          <div className="modal">
            <h2>Add Customer</h2>
            <form onSubmit={handleAddCustomer}>
              <div className="input-group">
                <label>First Name</label>
                <input required value={addForm.firstName} onChange={e => setAddForm(f => ({ ...f, firstName: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>Last Name</label>
                <input required value={addForm.lastName} onChange={e => setAddForm(f => ({ ...f, lastName: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>Email</label>
                <input required type="email" value={addForm.email} onChange={e => setAddForm(f => ({ ...f, email: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>SSN</label>
                <input required value={addForm.ssn} onChange={e => setAddForm(f => ({ ...f, ssn: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>Status</label>
                <select value={addForm.status} onChange={e => setAddForm(f => ({ ...f, status: e.target.value }))}>
                  <option value="ACTIVE">ACTIVE</option>
                  <option value="INACTIVE">INACTIVE</option>
                </select>
              </div>
              {addError && <div className="error-message">{addError}</div>}
              <div style={{ marginTop: 16, display: "flex", gap: 12 }}>
                <button type="submit" className="add-btn">Add</button>
                <button type="button" onClick={() => setShowAdd(false)}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}
      {loading && <p>Loading...</p>}
      {error && <p className="error-message">{error}</p>}
      {!loading && !error && (
        <table className="customer-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {customers.map((c) => (
              <tr key={c.id}>
                <td>{c.id}</td>
                <td>{c.firstName}</td>
                <td>{c.lastName}</td>
                <td>{c.email}</td>
                <td>{c.status}</td>
                <td>
                  <button className="edit-btn" onClick={() => handleEditClick(c)}>Edit</button>
                  <button className="delete-btn" onClick={() => handleDeleteClick(c.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {/* Edit Modal */}
      {editCustomer && (
        <div className="modal-backdrop">
          <div className="modal">
            <h2>Edit Customer</h2>
            <form onSubmit={handleEditSubmit}>
              <div className="input-group">
                <label>First Name</label>
                <input required value={editForm.firstName} onChange={e => setEditForm(f => ({ ...f, firstName: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>Last Name</label>
                <input required value={editForm.lastName} onChange={e => setEditForm(f => ({ ...f, lastName: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>Email</label>
                <input required type="email" value={editForm.email} onChange={e => setEditForm(f => ({ ...f, email: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>SSN</label>
                <input required value={editForm.ssn} onChange={e => setEditForm(f => ({ ...f, ssn: e.target.value }))} />
              </div>
              <div className="input-group">
                <label>Status</label>
                <select value={editForm.status} onChange={e => setEditForm(f => ({ ...f, status: e.target.value }))}>
                  <option value="ACTIVE">ACTIVE</option>
                  <option value="INACTIVE">INACTIVE</option>
                </select>
              </div>
              {editError && <div className="error-message">{editError}</div>}
              <div style={{ marginTop: 16, display: "flex", gap: 12 }}>
                <button type="submit" className="edit-btn">Save</button>
                <button type="button" onClick={() => setEditCustomer(null)}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Delete Confirmation */}
      {deleteId && (
        <div className="modal-backdrop">
          <div className="modal">
            <h2>Delete Customer</h2>
            <p>Are you sure you want to delete this customer?</p>
            {deleteError && <div className="error-message">{deleteError}</div>}
            <div style={{ marginTop: 16, display: "flex", gap: 12 }}>
              <button className="delete-btn" onClick={handleDeleteConfirm}>Delete</button>
              <button onClick={() => setDeleteId(null)}>Cancel</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Dashboard;
