#include "SM2026-YCbCr.h"

#include <SDL2/SDL.h>

#include "SM2026-Lab4.h"

void setYCbCr(int xx, int yy, float y, float cb, float cr) {
  float r = y + 1.402f * (cr - 128.0f);
  float g = y - 0.344136f * (cb - 128.0f) - 0.714136f * (cr - 128.0f);
  float b = y + 1.772f * (cb - 128.0f);

  Uint8 rNorm = normalizeUint8(r);
  Uint8 gNorm = normalizeUint8(g);
  Uint8 bNorm = normalizeUint8(b);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
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