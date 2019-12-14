<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!-- Bootstrap CSS CDN -->
<!--     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    Our Custom CSS


    Font Awesome JS
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script> -->
    <link rel="stylesheet" href="../resources/css/adminSideBar.css">
<!-- Sidebar  -->
        <nav id="sidebar">
            <div class="sidebar-header"><h3>Admin</h3>
            </div>

            <ul class="list-unstyled components">
                <li class="">
                    <a href="#boardSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                       <i class="fa fa-list-alt" aria-hidden="true"></i>
                       	게시판관리
                    </a>
                    <ul class="collapse list-unstyled" id="boardSubmenu">
                        <li>
                            <a href="#">공지사항</a>
                        </li>
                        <li>
                            <a href="#">거래소게시판</a>
                        </li>
                        <li>
                            <a href="#">1:1문의</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#">
                       <i class="fa fa-user" aria-hidden="true"></i>
                        	회원관리
                    </a>
                </li>

                <li>
                    <a href="#shopSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                       	거래관리
                    </a>
                    <ul class="collapse list-unstyled" id="shopSubmenu">
                        <li>
                            <a href="#">상품목록</a>
                        </li>
                        <li>
                            <a href="#">거래전체목록</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#Submenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                        <i class="fa fa-bar-chart" aria-hidden="true"></i>
                       	통계
                    </a>
                    <ul class="collapse list-unstyled" id="Submenu">
                        <li>
                            <a href="#">방문자통계</a>
                        </li>
                        <li>
                            <a href="#">거래통계</a>
                        </li>
                    </ul>
                </li>

            </ul>
        </nav>