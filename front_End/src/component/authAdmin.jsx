// AuthAdmin.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginAdmin } from "../api/apiBackEnd";

function AuthAdmin() {
  // Modifiez les noms des clés ici pour correspondre à votre entité Java
  const [adminData, setAdminData] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAdminData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const response = await loginAdmin(adminData); // Envoyez l'objet avec username/password
      // Comme discuté précédemment, response.data contiendra maintenant l'objet AuthResponse
      const resultData = response.data;

      console.log("Données de la réponse du backend:", resultData); // Vérifiez ce que vous recevez

      if (resultData && resultData.id && resultData.username) { // Vérifiez la propriété 'success' du AuthResponse
        navigate('/patients');
      } else {
        setError(resultData.message || 'Identifiants invalides'); // Utilisez le message du backend
      }
    } catch (err) {
      console.error("Erreur de connexion:", err);
      if (err.response) {
        setError(err.response.data?.message || 'Erreur du serveur');
      } else if (err.request) {
        setError('Impossible de se connecter au serveur. Vérifiez votre connexion.');
      } else {
        setError('Erreur inattendue: ' + err.message);
      }
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="username" // Changez le nom du champ pour qu'il corresponde à 'username'
          value={adminData.username}
          onChange={handleChange}
          placeholder="Nom d'utilisateur"
        />
        <input
          type="password"
          name="password" // Changez le nom du champ pour qu'il corresponde à 'password'
          value={adminData.password}
          onChange={handleChange}
          placeholder="Mot de passe"
        />
        <button type="submit">Se connecter</button>
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </form>
    </div>
  );
}

export default AuthAdmin;