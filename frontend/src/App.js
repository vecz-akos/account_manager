import { Route, Routes } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Layout from './components/Layout';
import Admin from './pages/Admin';
import Login from './pages/Login';
import Invoices from './pages/Invoices';
import ErrorPage from './pages/Error';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Login />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/invoices" element={<Invoices />} />
        <Route path="*" element={<ErrorPage />} />
      </Route>
    </Routes>
  );
}

export default App;
