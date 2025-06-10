import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client'; // <--- Correct import for createRoot
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import App from './App';
import AuthAdmin from './component/authAdmin';

import AllPatients from './component/AllPatients';
import AuthPatient from './component/authPatient';
import AlerteButton from './component/AlerteButton';

// Corrected: Use 'createRoot' from 'react-dom/client'
const root = createRoot(document.getElementById('root')); // <--- Use createRoot here
root.render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/admin" element={<AuthAdmin />} />
        <Route path="/patients" element={<AllPatients />} />
        <Route path="/patient" element={<AuthPatient />} />
        <Route path="/patient-dashboard" element={<AlerteButton />} />
      </Routes>
    </BrowserRouter>
  </StrictMode>
);