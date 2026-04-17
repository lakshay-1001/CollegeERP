import { useState } from "react";
import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import Features from "../components/Features";
import RoleSelection from "../components/RoleSelection";
import LoginModal from "../components/LoginModal";

export default function Home() {
  const [loginOpen, setLoginOpen] = useState(false);

  return (
    <div>
      <Navbar onLoginClick={() => setLoginOpen(true)} />
      <Hero />
      <Features />
      <RoleSelection />
      <LoginModal open={loginOpen} onClose={() => setLoginOpen(false)} />
    </div>
  );
}