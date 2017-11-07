<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only"> </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="stronaGlowna" class="navbar-brand" style="color: #353594;">Kilometrówka 2015</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="ewidencja"><b>Ewidencja</b></a>
                </li>

                <li>
                    <a href="przejazdy">Dodaj przejazd</a>
                </li>

                <li>
                    <a href="pracownicy">Pracownicy</a>
                </li>

                <li>
                    <a href="pojazdy">Pojazdy</a>
                </li>

                <li>
                    <a href="trasy">Trasy</a>
                </li>

                <li>
                    <a href="firmy">Firmy</a>
                </li>

                <li>
                    <a href="wyloguj">Witaj <b><c:out value="${sesja.login}" /></b> [WYLOGUJ]</a>
                </li>
            </ul>
        </div>
    </div>
</div>