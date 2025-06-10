import { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

export const useWebSocket = (userId) => {
  const [alerts, setAlerts] = useState([]);
  const [connectionStatus, setStatus] = useState('disconnected');
  const clientRef = useRef(null);

  useEffect(() => {
    console.log(`Initializing WebSocket for user ${userId}`);
    
    const socket = new SockJS('http://localhost:8080/ws');
    const client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      debug: (str) => console.debug(`STOMP: ${str}`),
      onConnect: () => {
        setStatus('connected');
        client.subscribe(`/user/${userId}/queue/alertes`, (message) => {
          console.log('New alert:', message.body);
          setAlerts(prev => [...prev, JSON.parse(message.body)]);
        });
      },
      onDisconnect: () => setStatus('disconnected'),
      onStompError: (frame) => {
        console.error('STOMP error:', frame.headers.message);
        setStatus('error');
      }
    });

    clientRef.current = client;
    client.activate();

    return () => {
      console.log('Cleaning up WebSocket');
      client.deactivate();
    };
  }, [userId]);

  return { alerts, connectionStatus };
};