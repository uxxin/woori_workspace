<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dev.syntax.model.User" %>
<%
    // 세션에서 로그인한 사용자 정보 가져오기
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    String libraryName = "밀리의서재";  // 기본값
    int currentUserId = 0;  // 로그인 안 했을 때
    String currentUserNickname = "";
    
    if (loggedInUser != null) {
        libraryName = loggedInUser.getNickname() + "의 서재";
        currentUserId = loggedInUser.getId();
        currentUserNickname = loggedInUser.getNickname();
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= libraryName %></title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Noto Sans KR", sans-serif;
            background-color: #f8f9fa;
            color: #333;
        }

        .header {
            background: white;
            padding: 12px 20px;
            border-bottom: 1px solid #e9ecef;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        .nav {
            display: flex;
            gap: 30px;
        }

        .nav a {
            text-decoration: none;
            color: #666;
            font-size: 14px;
            padding: 8px 0;
        }

        .nav a.active {
            color: #333;
            font-weight: 500;
        }

        .user-actions {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .notification {
            width: 20px;
            height: 20px;
            background: #ffc107;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 12px;
            color: white;
            cursor: pointer;
        }

        .login-btn {
            background: #333;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            font-size: 12px;
            cursor: pointer;
        }

        .user-info {
            display: none;
            align-items: center;
            gap: 10px;
        }

        .user-info.logged-in {
            display: flex;
        }

        .logged-in + .login-btn {
            display: none;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .main-content {
            display: flex;
            gap: 30px;
        }

        .sidebar {
            width: 280px;
        }

        .content {
            flex: 1;
        }

        .profile-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .profile-header {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .profile-avatar {
            width: 50px;
            height: 50px;
            background: #e9ecef;
            border-radius: 50%;
            margin-right: 12px;
            background-image: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50"><circle cx="25" cy="25" r="25" fill="%23ddd"/><circle cx="25" cy="20" r="8" fill="%23999"/><path d="M10 40c0-8 6-15 15-15s15 7 15 15" fill="%23999"/></svg>');
            background-size: cover;
        }

        .profile-info h3 {
            font-size: 16px;
            margin-bottom: 4px;
        }

        .profile-info p {
            font-size: 12px;
            color: #666;
        }

        .stats {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }

        .stat {
            text-align: center;
            cursor: pointer;
        }

        .stat-number {
            font-size: 18px;
            font-weight: bold;
            display: block;
        }

        .stat-label {
            font-size: 12px;
            color: #666;
        }

        .profile-actions {
            display: flex;
            gap: 10px;
        }

        .btn {
            padding: 8px 16px;
            border-radius: 4px;
            font-size: 12px;
            cursor: pointer;
            border: 1px solid #ddd;
            background: white;
            flex: 1;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .btn-dark {
            background: #333;
            color: white;
            border-color: #333;
        }

        .btn:hover {
            opacity: 0.8;
        }

        .alert {
            background: #fff3cd;
            border: 1px solid #ffeaa7;
            border-radius: 4px;
            padding: 12px;
            font-size: 12px;
            margin-bottom: 20px;
        }

        .section {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .section-title {
            font-size: 16px;
            font-weight: 500;
        }

        .section-link {
            font-size: 12px;
            color: #666;
            text-decoration: none;
            cursor: pointer;
        }

        .section-link:hover {
            color: #333;
        }

        .books-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
            gap: 15px;
        }

        .book-item {
            text-align: center;
            cursor: pointer;
            transition: transform 0.2s;
        }

        .book-item:hover {
            transform: translateY(-2px);
        }

        .book-cover {
            width: 100%;
            height: 160px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 4px;
            margin-bottom: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 12px;
            text-align: center;
            padding: 10px;
            position: relative;
            overflow: hidden;
        }

.book-cover {
    width: 100%;
    height: 160px;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-radius: 4px;
    margin-bottom: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 12px;
    text-align: center;
    padding: 10px;
    position: relative;
    overflow: hidden;
}

.book-cover img {
    width: 100%;
    height: 100%;
    object-fit: cover;  /* 이미지 비율 유지하면서 채우기 */
    border-radius: 4px;
    position: absolute;
    top: 0;
    left: 0;
}

/* 상태 뱃지가 이미지 위에 표시되도록 */
.book-status {
    position: absolute;
    top: 5px;
    right: 5px;
    padding: 2px 6px;
    border-radius: 12px;
    font-size: 10px;
    font-weight: bold;
    z-index: 10;  /* 이미지 위에 표시 */
}

        .book-title {
            font-size: 12px;
            margin-bottom: 4px;
            font-weight: 500;
            line-height: 1.3;
        }

        .book-author {
            font-size: 11px;
            color: #666;
        }

        .book-status {
            position: absolute;
            top: 5px;
            right: 5px;
            padding: 2px 6px;
            border-radius: 12px;
            font-size: 10px;
            font-weight: bold;
        }

        .status-reading {
            background: #28a745;
            color: #333;
        }

        .status-completed {
            background: #28a745;
            color: #333;
        }

        .status-to_read {
            background: #28a745;
            color: #333;
        }

        .reading-dot {
            position: absolute;
            bottom: 2px;
            width: 4px;
            height: 4px;
            background: #28a745;
            border-radius: 50%;
        }

        .progress-bar {
            width: 100%;
            height: 20px;
            background: #e9ecef;
            border-radius: 10px;
            overflow: hidden;
            margin: 10px 0;
        }

        .progress {
            height: 100%;
            background: linear-gradient(90deg, #6c757d 0%, #495057 100%);
            transition: width 0.3s ease;
        }

        .activity-icons {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }

        .activity-item {
            text-align: center;
            cursor: pointer;
            transition: transform 0.2s;
        }

        .activity-item:hover {
            transform: scale(1.05);
        }

        .activity-icon {
            width: 60px;
            height: 60px;
            background: #f8f9fa;
            border-radius: 50%;
            margin: 0 auto 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            transition: background 0.2s;
        }

        .activity-icon:hover {
            background: #e9ecef;
        }

        .bookshelf-books {
            display: flex;
            gap: 15px;
            overflow-x: auto;
            padding-bottom: 10px;
        }

        .bookshelf-book {
            flex-shrink: 0;
            width: 120px;
            text-align: center;
            cursor: pointer;
        }

        .empty-state {
            text-align: center;
            padding: 40px 20px;
            color: #666;
        }

        .empty-state-icon {
            font-size: 48px;
            margin-bottom: 16px;
        }

        .loading {
            display: none;
            text-align: center;
            padding: 20px;
            color: #666;
        }

        .loading.show {
            display: block;
        }

        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .main-content {
                flex-direction: column;
            }
            
            .sidebar {
                width: 100%;
            }
            
            .nav {
                gap: 15px;
            }
            
            .books-grid {
                grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
                gap: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">📚 밀리의서재</div>
        <nav class="nav">
            <a href="#" data-page="tutor">튜터</a>
            <a href="#" class="active" data-page="challenge">챌린지</a>
            <a href="#" data-page="search">검색</a>
            <a href="#" data-page="feed">피드</a>
            <a href="#" data-page="library">내서재</a>
            <a href="#" data-page="manage">관리</a>
        </nav>
    <div class="user-actions">
        <div class="notification" onclick="showNotifications()">
            <span id="notification-count">1</span>
        </div>
        
        <% if (loggedInUser == null) { %>
            <!-- 로그인 전 -->
            <button class="login-btn" onclick="showLogin()">로그인</button>
        <% } else { %>
            <!-- 로그인 후 -->
            <div class="user-info" style="display: flex; align-items: center; gap: 10px;">
                <span><%= loggedInUser.getNickname() %></span>
                <button onclick="logout()" style="background: none; border: 1px solid #333; 
                        padding: 5px 10px; border-radius: 4px; cursor: pointer;">로그아웃</button>
            </div>
        <% } %>
    </div>
</div>

    <div class="container">
        <div class="main-content">
            <div class="sidebar">
                <div class="profile-card">
                    <div class="profile-header">
                        <div class="profile-avatar" id="profile-avatar"></div>
                        <div class="profile-info">
                            <h3 id="user-nickname-display">책읽는효능</h3>
                            <p>
                                팔로잉 <span id="following-count">0</span> · 
                                팔로워 <span id="follower-count">0</span>
                            </p>
                        </div>
                    </div>
                    <div class="stats">
                        <div class="stat" onclick="showChallenges()">
                            <span class="stat-number" id="challenge-count">1</span>
                            <span class="stat-label">챌린지참여</span>
                        </div>
                        <div class="stat" onclick="showCompletedBooks()">
                            <span class="stat-number" id="completed-books-count">0</span>
                            <span class="stat-label">읽은책</span>
                        </div>
                        <div class="stat" onclick="showReadingBooks()">
                            <span class="stat-number" id="reading-books-count">0</span>
                            <span class="stat-label">독서중</span>
                        </div>
                        <div class="stat" onclick="showReviews()">
                            <span class="stat-number" id="review-count">1</span>
                            <span class="stat-label">리뷰</span>
                        </div>
                    </div>
                    <div class="profile-actions">
                        <a href="#" class="btn btn-dark" onclick="showWriteForm()">+ 글쓰기</a>
                        <a href="#" class="btn" onclick="showBookSearch()">내 도서검색</a>
                    </div>
                </div>

                <div class="alert" id="user-alert" onclick="location.href='/Millie/bookList'">
                    💡 독서 기록을 시작해보세요! 첫 번째 책을 추가해보세요.
                </div>
            </div>

            <div class="content">
                <!-- 도서 섹션 -->
                <div class="section">
                    <div class="section-header">
                        <h2 class="section-title">도서</h2>
                        <a href="#" class="section-link" onclick="showAllBooks()">전체보기 ></a>
                    </div>
                    <div class="loading" id="books-loading">📚 도서를 불러오는 중...</div>
                    <div class="books-grid" id="books-grid">
                        <!-- 서버에서 도서 데이터를 받아와서 동적으로 생성 -->
                    </div>
                    <div class="empty-state" id="books-empty" style="display: none;">
                        <div class="empty-state-icon">📚</div>
                        <p>아직 등록된 도서가 없습니다.</p>
                        <button class="btn btn-dark" onclick="addFirstBook()" style="margin-top: 10px;">첫 번째 책 추가하기</button>
                    </div>
                </div>

                <!-- 책장 섹션 -->
                <div class="section">
                    <div class="section-header">
                        <h2 class="section-title">책장</h2>
                        <a href="#" class="section-link" onclick="showBookshelf()">전체보기 ></a>
                    </div>
                    <div class="bookshelf-books" id="bookshelf-preview">
                        <!-- 서버에서 책장 데이터를 받아와서 동적으로 생성 -->
                    </div>
                </div>

                <!-- 독서 리포트 섹션 -->
                <div class="section">
                    <div class="section-header">
                        <h2 class="section-title">독서 리포트</h2>
                        <a href="#" class="section-link" onclick="showDetailedReport()">전체보기 ></a>
                    </div>
                    <p style="font-size: 12px; color: #666; margin-bottom: 20px;">독서 리포트로 당신의 독서 기록을 확인하세요</p>
                    
                    <div style="margin-bottom: 20px;">
                        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                            <span style="font-size: 14px;">내 월 평균 독서량</span>
                            <span style="font-size: 12px; color: #666;" id="user-monthly-average">0권/월</span>
                        </div>
                        <div class="progress-bar">
                            <div class="progress" id="user-progress" style="width: 0%;"></div>
                        </div>
                        <div style="font-size: 11px; color: #999; text-align: right; margin-top: 4px;">
                            목표: <span id="user-target">5</span>권/월
                        </div>
                    </div>

                    <div style="margin-bottom: 10px;">
                        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                            <span style="font-size: 14px;">전체 이용자 평균</span>
                            <span style="font-size: 12px; color: #666;" id="global-average">1.8권/월</span>
                        </div>
                        <div class="progress-bar">
                            <div class="progress" id="global-progress" style="width: 70%; background: linear-gradient(90deg, #28a745 0%, #20c997 100%);"></div>
                        </div>
                        <div style="font-size: 11px; color: #999; text-align: right; margin-top: 4px;">
                            상위 <span id="user-rank">57</span>%
                        </div>
                    </div>
                </div>

                <!-- 독서 활동 섹션 -->
                <div class="section">
                    <div class="section-header">
                        <h2 class="section-title">독서 활동</h2>
                    </div>
                    <div class="activity-icons">
                        <div class="activity-item" onclick="showCollections()">
                            <div class="activity-icon">❤️</div>
                            <div style="font-size: 12px;">북라뜬</div>
                            <div style="font-size: 12px;">컬렉션읽기</div>
                        </div>
                        <div class="activity-item" onclick="showReadingClubs()">
                            <div class="activity-icon">☕</div>
                            <div style="font-size: 12px;">Latte 독서모임</div>
                            <div style="font-size: 12px;">참가하기</div>
                        </div>
                        <div class="activity-item" onclick="showWriteDiary()">
                            <div class="activity-icon">📝</div>
                            <div style="font-size: 12px;">독서 일지 쓰기</div>
                            <div style="font-size: 12px;">책리 리뷰쓰기</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>

    const contextPath = '<%=request.getContextPath()%>';
    <% if (loggedInUser != null) { %>
        // 로그인한 경우
        let currentUserId = '<%= loggedInUser.getId() %>';
        let currentUserNickname = '<%= loggedInUser.getNickname() %>';
        let isLoggedIn = true;
    <% } else { %>
        // 로그인하지 않은 경우 - 기본값 설정하지 않음!
        let currentUserId = null;
        let currentUserNickname = null;
        let isLoggedIn = false;
    <% } %>

    // 페이지 로드 시 초기화
    document.addEventListener('DOMContentLoaded', function() {
        if (isLoggedIn) {
            // 로그인한 경우에만 사용자 정보 표시
            document.getElementById('user-nickname-display').textContent = currentUserNickname;
            // 사용자 선택 드롭다운 숨기기
            const selector = document.getElementById('user-selector');
            if (selector) {
                selector.style.display = 'none';
            }
        } else {
            // 로그인하지 않은 경우 - 게스트 표시
            document.getElementById('user-nickname-display').textContent = '게스트';
            // 통계 숨기기 또는 0으로 표시
            document.getElementById('challenge-count').textContent = '0';
            document.getElementById('completed-books-count').textContent = '0';
            document.getElementById('reading-books-count').textContent = '0';
            document.getElementById('review-count').textContent = '0';
        }
        
        initializePage();
    });
 // 페이지 초기화
 function initializePage() {
     checkLoginStatus();
     loadInitialData();
     setupEventListeners();
 }

 // 로그인 상태 확인
 function checkLoginStatus() {
     // 현재는 로컬 스토리지 기반
     updateUserDisplay();
 }


 // 사용자 이름 업데이트 *** 새로 추가 ***
 function updateUserDisplay() {
     const userNames = {
         '1': '김윤서',
         '2': '양유진',
         '3': '신수연',
         '4': '문원규'
     };
     
     const userName = userNames[currentUserId] || '사용자';
     
     const displayEl = document.getElementById('user-nickname-display');
     if (displayEl) {
         displayEl.textContent = userName;
     }
     
     // 팔로잉/팔로워 수 랜덤 설정
     const followingEl = document.getElementById('following-count');
     const followerEl = document.getElementById('follower-count');
     if (followingEl) followingEl.textContent = Math.floor(Math.random() * 20);
     if (followerEl) followerEl.textContent = Math.floor(Math.random() * 50);
 }

 // 초기 데이터 로드
 function loadInitialData() {
     loadUserBooks();
     loadBookshelfPreview();  // 이 함수 정의 추가
     loadReadingRecords();
     loadUserStats();
 }

 // 사용자 도서 목록 로드 *** userId 파라미터 추가 ***
 function loadUserBooks() {
     const loadingEl = document.getElementById('books-loading');
     if (loadingEl) {
         loadingEl.classList.add('show');
     }
     
     fetch(contextPath + '/book/list?userId=' + currentUserId, {
         method: 'GET',
         headers: {
             'Content-Type': 'application/json',
         }
     })
     .then(response => {
         if (!response.ok) throw new Error('Network response was not ok');
         return response.json();
     })
     .then(data => {
         userBooks = data;
         renderBooksGrid(data);
         if (loadingEl) {
             loadingEl.classList.remove('show');
         }
     })
     .catch(error => {
         console.error('도서 로드 실패:', error);
         loadMockBooks();
     });
 }

 // 책장 미리보기 로드 *** 이 함수가 없었음! ***
 function loadBookshelfPreview() {
     fetch(contextPath + '/bookshelf/preview?userId=' + currentUserId, {
         method: 'GET',
         headers: {
             'Content-Type': 'application/json',
         }
     })
     .then(response => response.json())
     .then(data => {
         renderBookshelfPreview(data);
     })
     .catch(error => {
         console.error('책장 로드 실패:', error);
         // 실패 시 기본 책장 표시
         renderBookshelfPreview([{
             id: 1,
             name: '내 책장',
             bookCount: 0,
             colorClass: 'orange'
         }]);
     });
 }

 // 독서 기록 로드
 function loadReadingRecords() {
     fetch(contextPath + '/user/reading-records?userId=' + currentUserId, {
         method: 'GET',
         headers: {
             'Content-Type': 'application/json',
         }
     })
     .then(response => response.json())
     .then(data => {
         readingRecords = data;
         updateCalendarWithRecords();
     })
     .catch(error => {
         console.error('독서 기록 로드 실패:', error);
         readingRecords = [];
     });
 }

 // 사용자 통계 로드
 function loadUserStats() {
     fetch(contextPath + '/user/stats?userId=' + currentUserId, {
         method: 'GET',
         headers: {
             'Content-Type': 'application/json',
         }
     })
     .then(response => response.json())
     .then(data => {
         updateUserStats(data);
     })
     .catch(error => {
         console.error('통계 로드 실패:', error);
         // 기본값 설정
         updateUserStats({
             challengeCount: 1,
             completedBooks: 0,
             readingBooks: 2,
             reviewCount: 0,
             monthlyAverage: 2.3,
             progressPercentage: 46,
             target: 5,
             rank: 57
         });
     });
 }

 // 임시 데이터 로드 (개발용)
 function loadMockBooks() {
     const mockBooks = [
         {
             id: 1,
             title: 'Harry Potter',
             author: 'J.K. Rowling',
             book_cover: null,
             category: 'Fantasy',
             reading_status: 'reading',
             colorClass: 'dark'
         },
         {
             id: 2,
             title: '1984',
             author: 'George Orwell',
             book_cover: null,
             category: 'Dystopian',
             reading_status: 'completed',
             colorClass: ''
         }
     ];
     userBooks = mockBooks;
     renderBooksGrid(mockBooks);
     const loadingEl = document.getElementById('books-loading');
     if (loadingEl) {
         loadingEl.classList.remove('show');
     }
 }

 function renderBooksGrid(books) {
	    const grid = document.getElementById('books-grid');
	    if (!grid) return;
	    
	    if (books.length === 0) {
	        showEmptyBooksState();
	        return;
	    }

	    grid.innerHTML = books.map(book => {
	        // 이미지 경로 처리
	        let coverContent = '';
	        if (book.book_cover && book.book_cover !== 'null') {
	            // contextPath를 포함한 전체 경로
	            const imagePath = contextPath + '/' + book.book_cover;
	            coverContent = '<img src="' + imagePath + '" alt="' + book.title + '" ' +
	                          'style="width: 100%; height: 100%; object-fit: cover;" ' +
	                          'onerror="this.style.display=\'none\'; this.parentElement.insertAdjacentHTML(\'beforeend\', \'' + book.title + '\')">';
	        } else {
	            coverContent = book.title;
	        }
	        
	        return '<div class="book-item" onclick="showBookDetail(' + book.id + ')">' +
	            '<div class="book-cover ' + (book.colorClass || '') + '" data-book-id="' + book.id + '">' +
	                coverContent +
	                (book.reading_status ? 
	                    '<div class="book-status status-' + book.reading_status + '">' +
	                        getStatusText(book.reading_status) +
	                    '</div>' : ''
	                ) +
	            '</div>' +
	            '<div class="book-title">' + book.title + '</div>' +
	            '<div class="book-author">' + book.author + '</div>' +
	            '<div class="book-category" style="font-size: 10px; color: #999;">' + (book.category || '') + '</div>' +
	        '</div>';
	    }).join('');
	}

 // 책장 미리보기 렌더링 *** 이 함수도 정의 ***
 function renderBookshelfPreview(shelves) {
     const container = document.getElementById('bookshelf-preview');
     if (!container) return;
     
     container.innerHTML = shelves.map(shelf => 
         '<div class="bookshelf-book" onclick="showShelfDetail(' + shelf.id + ')">' +
             '<div class="book-cover ' + (shelf.colorClass || '') + '" style="height: 120px;">' +
                 (shelf.coverImage ? 
                     '<img src="' + shelf.coverImage + '" alt="' + shelf.name + '">' : 
                     shelf.name
                 ) +
             '</div>' +
             '<div class="book-title" style="margin-top: 8px;">' + shelf.name + '</div>' +
             '<div class="book-author">' + shelf.bookCount + '권</div>' +
         '</div>'
     ).join('');
 }

 // 상태 텍스트 변환
 function getStatusText(status) {
     const statusMap = {
         'reading': '읽는중',
         'completed': '완독',
         'to_read': '읽고싶음'
     };
     return statusMap[status] || '';
 }

 // 빈 도서 상태 표시
 function showEmptyBooksState() {
     const grid = document.getElementById('books-grid');
     const empty = document.getElementById('books-empty');
     if (grid) grid.style.display = 'none';
     if (empty) empty.style.display = 'block';
 }

 // 사용자 통계 업데이트
 function updateUserStats(stats) {
     document.getElementById('challenge-count').textContent = stats.challengeCount || 0;
     document.getElementById('completed-books-count').textContent = stats.completedBooks || 0;
     document.getElementById('reading-books-count').textContent = stats.readingBooks || 0;
     document.getElementById('review-count').textContent = stats.reviewCount || 0;
     
     document.getElementById('user-monthly-average').textContent = (stats.monthlyAverage || 0) + '권/월';
     document.getElementById('user-progress').style.width = (stats.progressPercentage || 0) + '%';
     document.getElementById('user-target').textContent = stats.target || 5;
     document.getElementById('user-rank').textContent = stats.rank || 0;
 }
 // 이벤트 리스너 설정
 function setupEventListeners() {
     const monthSelector = document.getElementById('month-selector');
     if (monthSelector) {
         monthSelector.addEventListener('change', function() {
             currentMonth = parseInt(this.value);
             generateCalendar(currentMonth, currentYear);
         });
     }
 }
 function showLogin() {
	    window.location.href = contextPath + '/login.jsp';
	}

	// 로그아웃
	function logout() {
	    if (confirm('로그아웃 하시겠습니까?')) {
	    	localStorage.removeItem('selectedUserId'); location.reload();
	    	window.location.href = contextPath + '/LogoutServlet';
	    }
	}

 </script>
</body>
</html>