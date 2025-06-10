import React, { useState, useEffect } from 'react';
import { allPatients } from "../api/apiBackEnd";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

export default function AllPatients() {
  const [patients, setPatients] = useState([]);
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    

    let client = null;
    const socket = new SockJS('http://localhost:8090/ws');
    client = Stomp.over(socket);

    client.connect({}, (frame) => {
      console.log('✅ Connecté au WebSocket : ' + frame);

      // 🔁 Remplace {patientId} dynamiquement
      client.subscribe(`/topic/alertes/medcin/1`, (message) => {
        const notification = message.body;
        console.log("🔔 Alerte reçue : " + notification);
        setNotifications(prev => [...prev, notification]);
      });
    }, (error) => {
      console.error('❌ Erreur de connexion WebSocket : ', error);
    });

    return () => {
      if (client && client.connected) {
        console.log("🔌 Déconnexion WebSocket...");
        client.disconnect(() => console.log("✅ Déconnecté"));
      }
    };
  }, []);

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const response = await allPatients();
        setPatients(response.data);
      } catch (err) {
        console.error("❌ Erreur fetch patients :", err);
        setError(err.message || "Échec du chargement.");
      } finally {
        setLoading(false);
      }
    };

    fetchPatients();
  }, []);

  if (loading) return <div>Chargement des patients...</div>;
  if (error) return <div style={{ color: 'red' }}>Erreur : {error}</div>;
  if (patients.length === 0) return <div>Aucun patient trouvé.</div>;

  return (
    <div>
      <h2>Liste des Patients</h2>

      {/* 🔔 Section pour afficher les notifications reçues */}
      {notifications.length > 0 && (
        <div style={{ marginBottom: '20px', padding: '10px', backgroundColor: '#fff3cd', border: '1px solid #ffeeba', borderRadius: '8px' }}>
          <h4>🔔 Alertes Reçues :</h4>
          <ul>
            {notifications.map((notif, index) => (
              <li key={index}>{notif}</li>
            ))}
          </ul>
        </div>
      )}

      <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2' }}>
            <th style={{ border: '1px solid #ddd', padding: '8px' }}>ID</th>
            <th style={{ border: '1px solid #ddd', padding: '8px' }}>Date de Naissance</th>
          </tr>
        </thead>
        <tbody>
          {patients.map(patient => (
            <tr key={patient.id}>
              <td style={{ border: '1px solid #ddd', padding: '8px' }}>{patient.id}</td>
              <td style={{ border: '1px solid #ddd', padding: '8px' }}>
                {patient.date_naissance ? new Date(patient.date_naissance).toLocaleDateString() : 'N/A'}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
