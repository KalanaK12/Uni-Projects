import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../../Pages/Navbar";

export default function Home() {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('/shop-products');
  }, []);

  return (
    null
  );
}
