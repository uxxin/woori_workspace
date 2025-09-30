import Header from "@/components/3.streaming-ssr/Header";
import Sidebar from "@/components/3.streaming-ssr/Sidebar";

export default function Layout({ children }) {
  return (
    <section className="min-h-screen flex">
      <aside className="w-64 bg-gray-100 border-r p-4">
        <Sidebar />
      </aside>

      <div className="flex-1 flex flex-col">
        <header className="h-16 bg-white border-b flex items-center justify-between px-6">
          <Header />
        </header>
        <main className="flex-1 p-6">{children}</main>
      </div>
    </section>
  );
}
