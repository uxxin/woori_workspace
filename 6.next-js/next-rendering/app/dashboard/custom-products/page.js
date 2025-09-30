// 재갱신 주기
export const revalidate = 60; // 1분 단위로 재갱신

export const dynamicParams = true;

export async function generateStaticParams() {
  const products = await fetch("http://localhost:4000/products").then((res) =>
    res.json()
  );
  return products.map((product) => ({
    id: String(product.id),
  }));
}

export default async function Page({ params }) {
  const { id } = await params;
  const product = await fetch(`http://localhost:4000/products/${id}`).then(
    (res) => res.json()
  );

  return (
    <main className="max-w-3xl mx-auto p-8 bg-white shadow-md rounded-lg mt-10">
      <h1 className="text-3xl font-bold mb-4 text-gray-800">{product.title}</h1>
      <p className="text-xl text-green-600 font-semibold">₩{product.price}</p>
    </main>
  );
}
