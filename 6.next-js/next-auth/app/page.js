import Link from "next/link";

export default function Home() {
  return (
    <main className="flex flex-col items-center mt-4">
      <div className="text-2xl font-bold">Hello Next Auth!</div>
      <Link
        href="/login"
        className="inline-block px-6 py-2 bg-blue-500 text-white"
      >
        Login
      </Link>
    </main>
  );
}
