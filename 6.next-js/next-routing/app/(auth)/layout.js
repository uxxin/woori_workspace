"use client";
//usePathname은 client component에서만 가능하기 때문에 이걸 써줘야 함

import { usePathname } from "next/navigation";
import Link from "next/link";

const navLinks = [
  { name: "Register", href: "/register" },
  { name: "Login", href: "/login" },
  { name: "Forgot-Password?", href: "/forgot-password" },
];

export default function Layout({ children }) {
  const pathName = usePathname();
  console.log(pathName);

  return (
    <section>
      {navLinks.map((link) => {
        const isActive = pathName.startsWith(link.href);
        return (
          <Link
            className={`text-2xl ${isActive ? "text-red-500" : ""}`}
            href={link.href}
            key={link.name}
          >
            {link.name}&nbsp;
          </Link>
        );
      })}
      {children}
    </section>
  );
}
