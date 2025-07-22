import { cookies } from "next/headers";
import { NextRequest, NextResponse } from "next/server";
import { decrypt } from "./app/lib/session";

const protectedRoutes = ["/dashboard"]; // 인증된 사용자만 접근할 수 있는 라우트 경로 모음
const publicRoutes = ["/login", "/"]; // 별도의 인증 없이도 접근할 수 있는 경로 모음

// 라우트가 수행되기 전에 호출되는 곳
// ex. lh:3-/login, lh:3-/dashboard라든지, 특정 라우트에 대한 렌더링 요청이 수행될 때
export default async function middleware(req) {
  const path = req.nextUrl.pathname;
  const isProtectedRoute = protectedRoutes.includes(path);
  const isPublicRoute = publicRoutes.includes(path); // 현재 요청 경로가 public인지, protected인지

  const cookie = (await cookies()).get("session")?.value; // 쿠키에서 토큰(session)을 추출
  const session = await decrypt(cookie); // JWT 복호화(userId값 확인하기 위해)

  if (isProtectedRoute && !session?.userId) {
    // 보호된 라우트이고, 인증되지 않은 사용자의 경우
    return NextResponse.redirect(new URL("/login", req.nextUrl));
  }

  if (isPublicRoute && session?.userId) {
    // 퍼블릭 라우트이고, userId가 존재할 경우
    return NextResponse.redirect(new URL("/dashboard", req.nextUrl));
  }

  return NextResponse.next();
}
