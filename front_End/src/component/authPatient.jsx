import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginPatient } from "../api/apiBackEnd";

function AuthPatient() {
  const [patientId, setPatientId] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setPatientId(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    if (!patientId || isNaN(patientId)) {
      setError('Veuillez saisir un ID de patient valide.');
      return;
    }

    try {
      const response = await loginPatient(patientId);
      const authenticatedPatient = response.data;

      console.log("Données de la réponse du backend (Patient authentifié):", authenticatedPatient);

      if (authenticatedPatient && authenticatedPatient.id) {
        // ** Changement ici : stockage dans sessionStorage **
        sessionStorage.setItem('loggedInPatient', JSON.stringify(authenticatedPatient));
        console.log('Patient connecté et informations stockées dans sessionStorage:', authenticatedPatient);

        navigate('/patient-dashboard');
      } else {
        setError('Identifiant patient invalide ou non trouvé.');
      }

    } catch (err) {
      console.error("Erreur de connexion patient:", err);
      if (err.response) {
        setError(err.response.data?.message || 'Identifiant patient incorrect ou non trouvé.');
      } else if (err.request) {
        setError('Impossible de se connecter au serveur. Vérifiez votre connexion.');
      } else {
        setError('Erreur inattendue: ' + err.message);
      }
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '50px auto', border: '1px solid #ccc', borderRadius: '8px' }}>
      <h2>Connexion Patient</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="patientId">Votre ID Patient:</label>
          <input
            type="number"
            id="patientId"
            name="id"
            value={patientId}
            onChange={handleChange}
            placeholder="Saisissez votre ID"
            required
            style={{ width: '100%', padding: '8px', margin: '5px 0' }}
          />
        </div>
        <button type="submit" style={{ padding: '10px 15px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
          Se connecter
        </button>
        {error && <p style={{ color: 'red', marginTop: '10px' }}>{error}</p>}
      </form>
    </div>
  );
}

export default AuthPatient;
