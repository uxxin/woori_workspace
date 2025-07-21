import Link from "next/link";

export default function Home() {
  return (
    <div className="absolute top-8 left-8 flex flex-col gap-4 text-3xl">
      <Link href="/dashboard/1.csr" className="hover:underline text-blue-600">
        1. CSR
      </Link>
      <br />
      <Link href="/dashboard/2.ssr" className="hover:underline text-blue-600">
        2. SSR
      </Link>
      <br />
      <Link
        href="/dashboard/3.streaming-ssr"
        className="hover:underline text-blue-600"
      >
        3. Streaming-SSR
      </Link>
      <br />
      <Link href="/dashboard/4.rsc" className="hover:underline text-blue-600">
        4. RSC
      </Link>
    </div>
  );
}
