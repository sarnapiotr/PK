#include <SDL2/SDL.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <exception>
#include <iostream>
using namespace std;

SDL_Window* window = NULL;
SDL_Surface* screen = NULL;

#define szerokosc 640
#define wysokosc 400

#define tytul "SM2026 - Lab1 - Sarna Piotr"

struct YUV {
  float m_y;
  float m_u;
  float m_v;

  YUV(float y = 0.0f, float u = 0.0f, float v = 0.0f)
      : m_y(y), m_u(u), m_v(v) {}
};

struct YIQ {
  float m_y;
  float m_i;
  float m_q;

  YIQ(float y = 0.0f, float i = 0.0f, float q = 0.0f)
      : m_y(y), m_i(i), m_q(q) {}
};

struct YCbCr {
  float m_y;
  float m_cb;
  float m_cr;

  YCbCr(float y = 0.0f, float cb = 0.0f, float cr = 0.0f)
      : m_y(y), m_cb(cb), m_cr(cr) {}
};

void setPixel(int x, int y, Uint8 R, Uint8 G, Uint8 B);
SDL_Color getPixel(int x, int y);

void linia(int x1, int y1, int x2, int y2, Uint8 R, Uint8 G, Uint8 B);

void czyscEkran(Uint8 R, Uint8 G, Uint8 B);

void Funkcja1();
void Funkcja2();
void Funkcja3();
void Funkcja4();
void Funkcja5();
void Funkcja6();
void Funkcja7();
void Funkcja8();
void Funkcja9();

void setYUV(int xx, int yy, float y, float u, float v);
void setYIQ(int xx, int yy, float y, float i, float q);
void setYCbCr(int xx, int yy, float y, float cb, float cr);

YUV getYUV(int xx, int yy);
YIQ getYIQ(int xx, int yy);
YCbCr getYCbCr(int xx, int yy);

Uint8 normalizeUint8(float val);

// RGB -> YUV -> RGB
void Funkcja1() {
  for (int yy = 0; yy < wysokosc / 2; yy++) {
    for (int xx = 0; xx < szerokosc / 2; xx++) {
      YUV yuv = getYUV(xx, yy);
      setYUV(xx + szerokosc / 2, yy, yuv.m_y, yuv.m_u, yuv.m_v);
    }
  }

  SDL_UpdateWindowSurface(window);
}

// RGB -> YIQ -> RGB
void Funkcja2() {
  for (int yy = 0; yy < wysokosc / 2; yy++) {
    for (int xx = 0; xx < szerokosc / 2; xx++) {
      YIQ yiq = getYIQ(xx, yy);
      setYIQ(xx, yy + wysokosc / 2, yiq.m_y, yiq.m_i, yiq.m_q);
    }
  }

  SDL_UpdateWindowSurface(window);
}

// RGB -> YCbCr -> RGB
void Funkcja3() {
  for (int yy = 0; yy < wysokosc / 2; yy++) {
    for (int xx = 0; xx < szerokosc / 2; xx++) {
      YCbCr ycbcr = getYCbCr(xx, yy);
      setYCbCr(xx + szerokosc / 2, yy + wysokosc / 2, ycbcr.m_y, ycbcr.m_cb,
               ycbcr.m_cr);
    }
  }

  SDL_UpdateWindowSurface(window);
}

void Funkcja4() {
  //...

  SDL_UpdateWindowSurface(window);
}

void Funkcja5() {
  //...

  SDL_UpdateWindowSurface(window);
}

void Funkcja6() {
  //...

  SDL_UpdateWindowSurface(window);
}

void Funkcja7() {
  //...

  SDL_UpdateWindowSurface(window);
}

void Funkcja8() {
  //...

  SDL_UpdateWindowSurface(window);
}

void Funkcja9() {
  //...

  SDL_UpdateWindowSurface(window);
}

void linia(int x1, int y1, int x2, int y2, Uint8 R, Uint8 G, Uint8 B) {
  int dx = x2 - x1;
  int dy = y2 - y1;

  int kx = 1;
  int ky = 1;

  if (dx < 0) {
    dx = x1 - x2;
    kx = -1;
  }

  if (dy < 0) {
    dy = y1 - y2;
    ky = -1;
  }

  int d = 0;

  int x = x1;  //
  int y = y1;  //

  setPixel(x, y, R, G, B);

  if (dx >= dy) {
    d = 2 * dy - dx;

    while (x != x2) {
      if (d >= 0) {
        x += kx;
        y += ky;
        d = d + 2 * (dy - dx);
      } else {
        x += kx;
        d = d + 2 * dy;
      }
      setPixel(x, y, R, G, B);
    }
  } else {
    d = 2 * dx - dy;
    while (y != y2) {
      if (d >= 0) {
        y += ky;
        x += kx;
        d = d + 2 * (dx - dy);
      } else {
        y += ky;
        d = d + 2 * dx;
      }
      setPixel(x, y, R, G, B);
    }
  }

  SDL_UpdateWindowSurface(window);
}

void setPixel(int x, int y, Uint8 R, Uint8 G, Uint8 B) {
  if ((x >= 0) && (x < szerokosc) && (y >= 0) && (y < wysokosc)) {
    /* Zamieniamy poszczególne składowe koloru na format koloru piksela */
    Uint32 pixel = SDL_MapRGB(screen->format, R, G, B);

    /* Pobieramy informację ile bajtów zajmuje jeden piksel */
    int bpp = screen->format->BytesPerPixel;

    /* Obliczamy adres piksela */
    Uint8* p1 =
        (Uint8*)screen->pixels + (y * 2) * screen->pitch + (x * 2) * bpp;
    Uint8* p2 =
        (Uint8*)screen->pixels + (y * 2 + 1) * screen->pitch + (x * 2) * bpp;
    Uint8* p3 =
        (Uint8*)screen->pixels + (y * 2) * screen->pitch + (x * 2 + 1) * bpp;
    Uint8* p4 = (Uint8*)screen->pixels + (y * 2 + 1) * screen->pitch +
                (x * 2 + 1) * bpp;

    /* Ustawiamy wartość piksela, w zależnoœci od formatu powierzchni*/
    switch (bpp) {
      case 1:  // 8-bit
        *p1 = pixel;
        *p2 = pixel;
        *p3 = pixel;
        *p4 = pixel;
        break;

      case 2:  // 16-bit
        *(Uint16*)p1 = pixel;
        *(Uint16*)p2 = pixel;
        *(Uint16*)p3 = pixel;
        *(Uint16*)p4 = pixel;
        break;

      case 3:  // 24-bit
        if (SDL_BYTEORDER == SDL_BIG_ENDIAN) {
          p1[0] = (pixel >> 16) & 0xff;
          p1[1] = (pixel >> 8) & 0xff;
          p1[2] = pixel & 0xff;
          p2[0] = (pixel >> 16) & 0xff;
          p2[1] = (pixel >> 8) & 0xff;
          p2[2] = pixel & 0xff;
          p3[0] = (pixel >> 16) & 0xff;
          p3[1] = (pixel >> 8) & 0xff;
          p3[2] = pixel & 0xff;
          p4[0] = (pixel >> 16) & 0xff;
          p4[1] = (pixel >> 8) & 0xff;
          p4[2] = pixel & 0xff;
        } else {
          p1[0] = pixel & 0xff;
          p1[1] = (pixel >> 8) & 0xff;
          p1[2] = (pixel >> 16) & 0xff;
          p2[0] = pixel & 0xff;
          p2[1] = (pixel >> 8) & 0xff;
          p2[2] = (pixel >> 16) & 0xff;
          p3[0] = pixel & 0xff;
          p3[1] = (pixel >> 8) & 0xff;
          p3[2] = (pixel >> 16) & 0xff;
          p4[0] = pixel & 0xff;
          p4[1] = (pixel >> 8) & 0xff;
          p4[2] = (pixel >> 16) & 0xff;
        }
        break;

      case 4:  // 32-bit
        *(Uint32*)p1 = pixel;
        *(Uint32*)p2 = pixel;
        *(Uint32*)p3 = pixel;
        *(Uint32*)p4 = pixel;
        break;
    }
  }
}

void setPixelSurface(int x, int y, Uint8 R, Uint8 G, Uint8 B) {
  if ((x >= 0) && (x < szerokosc * 2) && (y >= 0) && (y < wysokosc * 2)) {
    /* Zamieniamy poszczególne składowe koloru na format koloru piksela */
    Uint32 pixel = SDL_MapRGB(screen->format, R, G, B);

    /* Pobieramy informację ile bajtów zajmuje jeden piksel */
    int bpp = screen->format->BytesPerPixel;

    /* Obliczamy adres piksela */
    Uint8* p = (Uint8*)screen->pixels + y * screen->pitch + x * bpp;

    /* Ustawiamy wartość piksela, w zależności od formatu powierzchni*/
    switch (bpp) {
      case 1:  // 8-bit
        *p = pixel;
        break;

      case 2:  // 16-bit
        *(Uint16*)p = pixel;
        break;

      case 3:  // 24-bit
        if (SDL_BYTEORDER == SDL_BIG_ENDIAN) {
          p[0] = (pixel >> 16) & 0xff;
          p[1] = (pixel >> 8) & 0xff;
          p[2] = pixel & 0xff;
        } else {
          p[0] = pixel & 0xff;
          p[1] = (pixel >> 8) & 0xff;
          p[2] = (pixel >> 16) & 0xff;
        }
        break;

      case 4:  // 32-bit
        *(Uint32*)p = pixel;
        break;
    }
  }
}

SDL_Color getPixel(int x, int y) {
  SDL_Color color;
  Uint32 col = 0;
  if ((x >= 0) && (x < szerokosc) && (y >= 0) && (y < wysokosc)) {
    // określamy pozycję
    char* pPosition = (char*)screen->pixels;

    // przesunięcie względem y
    pPosition += (screen->pitch * y * 2);

    // przesunięcie względem x
    pPosition += (screen->format->BytesPerPixel * x * 2);

    // kopiujemy dane piksela
    memcpy(&col, pPosition, screen->format->BytesPerPixel);

    // konwertujemy kolor
    SDL_GetRGB(col, screen->format, &color.r, &color.g, &color.b);
  }
  return (color);
}

SDL_Color getPixelSurface(int x, int y, SDL_Surface* surface) {
  SDL_Color color;
  Uint32 col = 0;
  if ((x >= 0) && (x < szerokosc) && (y >= 0) && (y < wysokosc)) {
    // określamy pozycję
    char* pPosition = (char*)surface->pixels;

    // przesunięcie względem y
    pPosition += (surface->pitch * y);

    // przesunięcie względem x
    pPosition += (surface->format->BytesPerPixel * x);

    // kopiujemy dane piksela
    memcpy(&col, pPosition, surface->format->BytesPerPixel);

    // konwertujemy kolor
    SDL_GetRGB(col, surface->format, &color.r, &color.g, &color.b);
  }
  return (color);
}

void ladujBMP(char const* nazwa, int x, int y) {
  SDL_Surface* bmp = SDL_LoadBMP(nazwa);
  if (!bmp) {
    printf("Unable to load bitmap: %s\n", SDL_GetError());
  } else {
    SDL_Color kolor;
    for (int yy = 0; yy < bmp->h; yy++) {
      for (int xx = 0; xx < bmp->w; xx++) {
        kolor = getPixelSurface(xx, yy, bmp);
        setPixel(xx, yy, kolor.r, kolor.g, kolor.b);
      }
    }
    SDL_FreeSurface(bmp);
    SDL_UpdateWindowSurface(window);
  }
}

void czyscEkran(Uint8 R, Uint8 G, Uint8 B) {
  SDL_FillRect(screen, 0, SDL_MapRGB(screen->format, R, G, B));
  SDL_UpdateWindowSurface(window);
}

void setYUV(int xx, int yy, float y, float u, float v) {
  float r = y + 1.13983f * v;
  float g = y - 0.39465f * u - 0.58060f * v;
  float b = y + 2.03211f * u;

  Uint8 rNorm = normalizeUint8(r);
  Uint8 gNorm = normalizeUint8(g);
  Uint8 bNorm = normalizeUint8(b);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
}

void setYIQ(int xx, int yy, float y, float i, float q) {
  float r = y + 0.956f * i + 0.619f * q;
  float g = y - 0.272f * i - 0.647f * q;
  float b = y - 1.106f * i + 1.703f * q;

  Uint8 rNorm = normalizeUint8(r);
  Uint8 gNorm = normalizeUint8(g);
  Uint8 bNorm = normalizeUint8(b);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
}

void setYCbCr(int xx, int yy, float y, float cb, float cr) {
  float r = y + 1.402f * (cr - 128.0f);
  float g = y - 0.344136f * (cb - 128.0f) - 0.714136f * (cr - 128.0f);
  float b = y + 1.772f * (cb - 128.0f);

  Uint8 rNorm = normalizeUint8(r);
  Uint8 gNorm = normalizeUint8(g);
  Uint8 bNorm = normalizeUint8(b);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
}

YUV getYUV(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r = color.r;
  Uint8 g = color.g;
  Uint8 b = color.b;

  float y = 0.299f * r + 0.587f * g + 0.114f * b;
  float u = -0.14713f * r - 0.28886f * g + 0.436f * b;
  float v = 0.615f * r - 0.51499f * g - 0.10001f * b;

  return YUV(y, u, v);
}

YIQ getYIQ(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r = color.r;
  Uint8 g = color.g;
  Uint8 b = color.b;

  float y = 0.299f * r + 0.587f * g + 0.114f * b;
  float i = 0.5959f * r - 0.2746f * g - 0.3213f * b;
  float q = 0.2115f * r - 0.5227f * g + 0.3112f * b;

  return YIQ(y, i, q);
}

YCbCr getYCbCr(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r = color.r;
  Uint8 g = color.g;
  Uint8 b = color.b;

  float y = 0.299f * r + 0.587f * g + 0.114f * b;
  float cb = 128.0f - 0.168736f * r - 0.331264f * g + 0.5f * b;
  float cr = 128.0f + 0.5f * r - 0.418688f * g - 0.081312f * b;

  return YCbCr(y, cb, cr);
}

Uint8 normalizeUint8(float val) {
  if (val < 0.0f) return 0;
  if (val > 255.0f) return 255;
  return static_cast<Uint8>(val + 0.5f);
}

int main(int argc, char* argv[]) {
  if (SDL_Init(SDL_INIT_EVERYTHING) != 0) {
    printf("SDL_Init Error: %s\n", SDL_GetError());
    return EXIT_FAILURE;
  }

  window =
      SDL_CreateWindow(tytul, SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
                       szerokosc * 2, wysokosc * 2, SDL_WINDOW_SHOWN);

  if (window == NULL) {
    printf("SDL_CreateWindow Error: %s\n", SDL_GetError());
    return EXIT_FAILURE;
  }

  screen = SDL_GetWindowSurface(window);
  if (screen == NULL) {
    fprintf(stderr, "SDL_GetWindowSurface Error: %s\n", SDL_GetError());
    return false;
  }
  SDL_UpdateWindowSurface(window);

  bool done = false;
  SDL_Event event;
  // główna pętla programu
  while (SDL_WaitEvent(&event)) {
    // sprawdzamy czy pojawiło się zdarzenie
    switch (event.type) {
      case SDL_QUIT:
        done = true;
        break;

      // sprawdzamy czy został wciśnięty klawisz
      case SDL_KEYDOWN: {
        // wychodzimy, gdy wciśnięto ESC
        if (event.key.keysym.sym == SDLK_ESCAPE) done = true;
        if (event.key.keysym.sym == SDLK_1) Funkcja1();
        if (event.key.keysym.sym == SDLK_2) Funkcja2();
        if (event.key.keysym.sym == SDLK_3) Funkcja3();
        if (event.key.keysym.sym == SDLK_4) Funkcja4();
        if (event.key.keysym.sym == SDLK_5) Funkcja5();
        if (event.key.keysym.sym == SDLK_6) Funkcja6();
        if (event.key.keysym.sym == SDLK_7) Funkcja7();
        if (event.key.keysym.sym == SDLK_8) Funkcja8();
        if (event.key.keysym.sym == SDLK_9) Funkcja9();
        if (event.key.keysym.sym == SDLK_a) ladujBMP("obrazek1.bmp", 0, 0);
        if (event.key.keysym.sym == SDLK_s) ladujBMP("obrazek2.bmp", 0, 0);
        if (event.key.keysym.sym == SDLK_d) ladujBMP("obrazek3.bmp", 0, 0);
        if (event.key.keysym.sym == SDLK_f) ladujBMP("obrazek4.bmp", 0, 0);
        if (event.key.keysym.sym == SDLK_g) ladujBMP("obrazek5.bmp", 0, 0);
        if (event.key.keysym.sym == SDLK_h) ladujBMP("obrazek6.bmp", 0, 0);
        if (event.key.keysym.sym == SDLK_b)
          czyscEkran(0, 0, 0);
        else
          break;
      }
    }
    if (done) break;
  }

  if (screen) {
    SDL_FreeSurface(screen);
  }

  if (window) {
    SDL_DestroyWindow(window);
  }

  SDL_Quit();
  return 0;
}