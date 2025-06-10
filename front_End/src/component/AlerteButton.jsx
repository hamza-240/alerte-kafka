import React, { useState, useEffect } from 'react';
import { sendAlerteClick } from '../api/apiBackEnd';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

export default function AlerteButton() {
  const [clickCount, setClickCount] = useState(0);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    const patientStr = sessionStorage.getItem('loggedInPatient'); // <-- ici sessionStorage
    if (!patientStr) {
      setError("Aucun patient connecté !");
      return;
    }

    const patient = JSON.parse(patientStr);
    const patientId = patient?.id_patient || patient?.id;

    if (!patientId) {
      setError("ID du patient non trouvé !");
      return;
    }

    let client = null;
    const socket = new SockJS('http://localhost:8090/ws');
    client = Stomp.over(socket);

    client.connect({}, (frame) => {
      console.log("✅ WebSocket connecté (AlerteButton)");

      client.subscribe(`/topic/alertes/patient/${patientId}`, (message) => {
        const notification = message.body;
        console.log("🔔 Alerte reçue (AlerteButton) : " + notification);
        setNotifications(prev => [...prev, notification]);
      });
    }, (error) => {
      console.error("❌ WebSocket erreur :", error);
    });

    return () => {
      if (client && client.connected) {
        console.log("🔌 Déconnexion WebSocket (AlerteButton)...");
        client.disconnect(() => console.log("✅ Déconnecté"));
      }
    };
  }, []);

  const handleClick = async () => {
    try {
      setError('');
      setMessage('');

      const patientStr = sessionStorage.getItem('loggedInPatient'); // <-- ici aussi sessionStorage
      const patient = patientStr ? JSON.parse(patientStr) : null;

      if (!patient || !(patient.id_patient || patient.id)) {
        setError("Patient non authentifié. Veuillez vous reconnecter.");
        return;
      }

      const response = await sendAlerteClick(clickCount, patient);
      const newCount = response.data;

      setClickCount(newCount);
      setMessage(`Nombre d'alertes envoyées : ${newCount}`);
    } catch (err) {
      console.error('Erreur lors de l’envoi de l’alerte:', err);
      setError("Impossible d'envoyer l'alerte. Veuillez réessayer.");
    }
  };

  return (
    <div style={{ marginTop: '50px', textAlign: 'center' }}>
      <button 
        onClick={handleClick}
        style={{
          padding: '12px 25px',
          backgroundColor: '#dc3545',
          color: '#fff',
          border: 'none',
          borderRadius: '8px',
          fontSize: '16px',
          cursor: 'pointer'
        }}
      >
        Alerte
      </button>

      {message && <p style={{ marginTop: '15px', color: 'green' }}>{message}</p>}
      {error && <p style={{ marginTop: '15px', color: 'red' }}>{error}</p>}

      {notifications.length > 0 && (
        <div style={{ marginTop: '20px', backgroundColor: '#fff3cd', padding: '10px', borderRadius: '8px' }}>
          <h4>🔔 Alertes reçues en temps réel :</h4>
          <ul>
            {notifications.map((notif, idx) => (
              <li key={idx}>{notif}</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
