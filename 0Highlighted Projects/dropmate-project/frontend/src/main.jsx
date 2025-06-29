import React from 'react'
import ReactDOM from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

// Pages
import App from './App.jsx'
import ACP from './pages/ACP/index.jsx';
import ACPOperator from './pages/ACP-Operator/index.jsx';
import Home from './pages/Home/index.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/admin",
    element: <App />,
  },
  {
    path: "/acp/:id",
    element: <ACP />,
  },
  {
    path: "/acp-operator",
    element: <ACPOperator />,
  },
  {
    path: "*",
    element: <Home />,
  }
],
{ basename: '/DropMate-UI/' }
);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
