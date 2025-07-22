"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

const Sidebar = () => {
  console.log("<Sidebar /> 렌더링 됨");
  const pathname = usePathname();

  const links = [
    { href: "/dashboard/1.csr", label: "CSR" },
    { href: "/dashboard/2.ssr", label: "SSR" },
    { href: "/dashboard/3.streaming-ssr", label: "Streaming SSR" },
    { href: "/dashboard/4.rsc", label: "React Server Component" },
  ];

  return (
    <nav className="flex flex-col gap-2">
      {links.map((link) => (
        <Link
          key={link.href}
          href={link.href}
          className={`p-2 rounded ${
            pathname === link.href
              ? "bg-blue-500 text-white"
              : "hover:bg-gray-200"
          }`}
        >
          {link.label}
        </Link>
      ))}
    </nav>
  );
};

export default Sidebar;
