// 캐싱 동작 테스트용 서버
const express = require("express");
const cors = require("cors");

const app = express();
const PORT = 4000;

app.use(cors());

function delay(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

const products = [
  {
    id: 1,
    title: "Product 1",
    price: 500,
    description: "Description 1",
  },
  {
    id: 2,
    title: "Product 2",
    price: 1000,
    description: "Description 2",
  },
  {
    id: 3,
    title: "Product 3",
    price: 2500,
    description: "Description 3",
  },
];

app.get("/products", async (req, res) => {
  console.log("/products 요청됨");

  const delayMs = Number(req.query.delay || 0); // ?delay=2000 같이 사용 가능
  if (delayMs > 0) await delay(delayMs);

  res.json(products);
});

app.get("/products/:id", async (req, res) => {
  const delayMs = Number(req.query.delay || 0);
  if (delayMs > 0) await delay(delayMs);

  const id = Number(req.params.id);
  const product = products.find((p) => p.id === id);

  if (!product) {
    return res.status(404).json({ error: "Product not found" });
  }

  console.log(`/products/${id} 요청됨`);
  res.json(product);
});

app.listen(PORT, () => {
  console.log(
    `Express API 서버가 http://localhost:${PORT} 에서 실행 중입니다.`
  );
});
