#include "SM2026-YIQ.h"

#include <SDL2/SDL.h>

#include "SM2026-Lab4.h"

void setYIQ(int xx, int yy, float y, float i, float q) {
  float r = y + 0.956f * i + 0.619f * q;
  float g = y - 0.272f * i - 0.647f * q;
  float b = y - 1.106f * i + 1.703f * q;

  Uint8 rNorm = normalizeUint8(r);
  Uint8 gNorm = normalizeUint8(g);
  Uint8 bNorm = normalizeUint8(b);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
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